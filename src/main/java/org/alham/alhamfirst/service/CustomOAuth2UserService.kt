package org.alham.alhamfirst.service

import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.mapper.UserMapper
import org.alham.alhamfirst.repository.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.uuid.Uuid

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {

        /**
         * 여기서 필요한건
         * 1. provider별로 제공하는 user의 unique 식별값
         * -> 이건 provider별로 제공하는
         * registrationId -> provider의 이름
         * -> provider별로 getAttributes()에서 제공하는 unique 식별값의 key가 다 다르다. 이걸 구분해줘야한다.
         */
        //provider에서 제공하는 유저 정보 가져오기
        val oauthUser = super.loadUser(userRequest).attributes

        //provider의 이름
        val registerId = userRequest?.clientRegistration?.registrationId ?: throw IllegalArgumentException("소셜 로그인을 지원하지 않는 클라이언트입니다.")


        //유저 정보 DTO 생성
        //securityContext에 저장할 객체를 가져오는곳
        //attributes에선 비지니스 로직에 사용할것 저장
        //맞춰서 튜닝
        val oAuthUserInfo = OAuthUserInfo.of(registerId, oauthUser)
        val user = getOrSave(oAuthUserInfo)

        return user
    }

    private fun getOrSave(oAuthUserInfo: OAuthUserInfo): DefaultOAuth2User{
        val userDTO = userRepository.findByOauthProviderAndOauthId(oAuthUserInfo.registerId, oAuthUserInfo.id)
            ?.let {
                UserMapper().createUserDTOFromEntity(it)
            }?: run{
                //만들어야하는건 UUID ->
                val uuid = UUID.randomUUID().toString()
                val newUserDTO = UserDTO(
                    uuid = uuid,
                    name = oAuthUserInfo.name,
                    email = oAuthUserInfo.email,
                    oauthProvider = oAuthUserInfo.registerId,
                    oauthId = oAuthUserInfo.id
                )
                val newUser = UserMapper().createNewUserFromDTO(newUserDTO)
                UserMapper().createUserDTOFromEntity(userRepository.save(newUser))
            }

        val attributes = mapOf(
            "uuid" to userDTO.uuid,
        )

        return DefaultOAuth2User(setOf(SimpleGrantedAuthority("ROLE_"+userDTO.userType)), attributes, userDTO.oauthId)
    }

}

/**
 * provider에서 제공한 정보 담는 클래스
 */
class OAuthUserInfo(
    val id: String,
    val name: String,
    val email: String,
    val registerId: String
){
    companion object{
        fun of(registerId: String, attributes: Map<String, Any>): OAuthUserInfo{
            return when(registerId){
                "google" -> ofGoogle(attributes, registerId)
                "facebook" -> ofFacebook(attributes, registerId)
                "apple" -> ofApple(attributes, registerId)
                else -> throw IllegalArgumentException("지원하지 않는 소셜 로그인입니다.")
            }
        }

        private fun ofGoogle(attributes: Map<String, Any>,registerId: String): OAuthUserInfo{
            return OAuthUserInfo(
                id = attributes["sub"] as String,
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                registerId = registerId
            )
        }

        private fun ofFacebook(attributes: Map<String, Any>, registerId: String): OAuthUserInfo{
            return OAuthUserInfo(
                id = attributes["id"] as String,
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                registerId = registerId
            )
        }

        private fun ofApple(attributes: Map<String, Any>,registerId: String): OAuthUserInfo{
            return OAuthUserInfo(
                id = attributes["sub"] as String,
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                registerId = registerId
            )
        }

    }

}
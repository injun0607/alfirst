package org.alham.alhamfirst.config.security;

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.alham.alhamfirst.common.enums.UserType
import org.alham.alhamfirst.domain.entity.User
import org.alham.alhamfirst.mapper.UserMapper
import org.alham.alhamfirst.repository.user.UserRepository
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil(
    private val userRepository: UserRepository
){
    /*
     * 기본적으로 JWT 토큰을 생생할때 비밀키가 필요한데, 보안과 호환성을 위해 일반문자열을 Encoding한 키로 사용한다.
     * 그후 SecretKey객체로 변경할때는 Encoding된 문자열을 Decoders.BASE64.decode()로 디코딩한후 사용한다
     */

    private val SECRETKEY: String = "AlhamAndSeoHee123213AlhamAndSeoHee123213"
    private val KEY: SecretKey = Keys.hmacShaKeyFor(SECRETKEY.toByteArray())


    //TODO - 비밀키 생성하는 로직은 바뀔수있음
    fun getSigningKey(): SecretKey{
        val encoded: String  = Base64.getEncoder().encodeToString(SECRETKEY.toByteArray());
        val keyBytes: ByteArray = Decoders.BASE64.decode(encoded);
        return Keys.hmacShaKeyFor(keyBytes)
    }

    /**
     * JWT 토큰 생성
     * @param id
     * @param subject
     * @return
     */
    fun generateToken(id: String, subject: String) :String{
        val now: Date = Date();
        return Jwts.builder()
                .id(id)
                .subject(subject)
                .issuedAt(now)
                .signWith(getSigningKey())
                .expiration(Date(now.time + 1000 * 60 * 60 * 10)) // 10시간
                .compact();
    }

    fun getSubject(token: String): String {
        return extractAllClaims(token).subject
    }

    //TODO - 임시해제 처리 -> 테스트용으로 무조건 통과
    fun getAuthentication(token: String): Authentication {
        val claims = extractAllClaims(token)
        val uuid = claims.subject

        val user = userRepository.findByUuid(uuid)
            ?: throw AuthenticationCredentialsNotFoundException("유저를 찾을 수 없습니다.")

//        val user = User(
//            id=1L,
//            userType = UserType.BASIC
//        )
        val userDTO = UserMapper().createUserDTOFromEntity(user)
        return UsernamePasswordAuthenticationToken(userDTO, null, setOf(SimpleGrantedAuthority("ROLE_"+userDTO.userType)))
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token);
    }
    fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date());
    }
    // 내부 메서드: Claims(payload 추출)
    private fun extractAllClaims(token: String): Claims{
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).payload;
    }

}

package org.alham.alhamfirst.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.alham.alhamfirst.repository.user.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomOAuth2SuccessHandler(
    private val userRepository: UserRepository,
    private val jwtUtil: JWTUtil
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oauth2User = authentication?.principal as OAuth2User
        val email = oauth2User.getAttribute<String>("email") ?: throw RuntimeException("email is null")

        //DB저장, 조회
//        val user = userRepository.findByEmail

        val id = "random_key"
        val token = jwtUtil.generateToken(id, email)
        response?.contentType = "application/json"
        response?.writer?.write("{\"token\": \"$token\"}")

    }


}
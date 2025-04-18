package org.alham.alhamfirst.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.alham.alhamfirst.repository.user.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomOAuth2SuccessHandler(
    private val jwtUtil: JWTUtil
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oauth2User = authentication?.principal as? OAuth2User
        val uuid = oauth2User?.getAttribute<String>("uuid") ?: throw RuntimeException("uuid is null")

        val id = UUID.randomUUID().toString()
        val token = jwtUtil.generateToken(id, uuid)
        response?.contentType = "application/json"

        response?.sendRedirect("http://localhost:3000/oauth2/success?token=$token")

    }


}
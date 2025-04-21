package org.alham.alhamfirst.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.alham.alhamfirst.repository.user.UserRepository
import org.springframework.http.ResponseCookie
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

        val cookie = ResponseCookie.from("accessToken",token)
            .httpOnly(true)
            .secure(false)           // 개발환경에서 false로 유지
            .sameSite("Lax")         // None → Lax 로 변경해보자
            .path("/")
            .maxAge(75)
            .build()


        response?.addHeader("Set-Cookie", cookie.toString())
        response?.sendRedirect("/login-success")

    }


}
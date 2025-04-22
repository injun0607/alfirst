package org.alham.alhamfirst.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
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

        val accessId = UUID.randomUUID().toString()
        val refreshId = UUID.randomUUID().toString()
        val accessToken = jwtUtil.generateAccessToken(accessId, uuid)
        val refreshToken = jwtUtil.generateRefreshToken(refreshId, uuid)

        response?.contentType = "application/json"

        val accessTokenCookie = ResponseCookie
            .from("accessToken",accessToken)
            .httpOnly(true)
            .secure(false)           // 개발환경에서 false로 유지
            .sameSite("Lax")         // None → Lax 로 변경해보자
            .path("/login/success-access")
            .maxAge(75)
            .build()

        val refreshTokenCookie = ResponseCookie
            .from("refreshToken",refreshToken)
            .httpOnly(true)
            .secure(false)           // 개발환경에서 false로 유지
            .sameSite("Lax")         // None → Lax 로 변경해보자
            .path("/login/refresh-access")
            .maxAge(60 * 60 * 24 * 14) // 7일
            .build()



        response?.addHeader("Set-Cookie", accessTokenCookie.toString())
        response?.addHeader("Set-Cookie", refreshTokenCookie.toString())
        response?.sendRedirect("/login-success")

    }


}
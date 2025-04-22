package org.alham.alhamfirst.filter;

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.config.security.JWTUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
@Component
class JwtRequestFilter(
    private val jwtUtil: JWTUtil
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)

        //TODO - 토큰체크를 해야하는곳
        if(token != null && jwtUtil.validateToken(token)){
            val auth = jwtUtil.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
            filterChain.doFilter(request, response)
//        if(true){
//            val auth = jwtUtil.getAuthentication("") // 임시 승인처리
        }else{
            returnUnauthorized(response)
            return
        }
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization")
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }

    private fun returnUnauthorized(response: HttpServletResponse) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(
            """
            {
                "code": 401,
                "message": "Access Token is invalid or expired."
            }
            """.trimIndent()
        )
    }


}

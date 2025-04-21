package org.alham.alhamfirst.controller;


/*
 * 로그인 방식은 OAuth2.0을 사용한다.
 * JWT 토큰을 통해 API 로 통신
 */

import jakarta.servlet.http.HttpServletRequest
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.config.security.JWTUtil
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class LoginController(
    private val jwtUtil: JWTUtil
) {

    private val log = logger()

    @GetMapping("/login")
    fun login():String {
        return "login";
    }

    @GetMapping("/login-success")
    fun loginSuccess():String {
        return "login-success";
    }

    @GetMapping("/login/success-access")
    @ResponseBody
    fun loginSuccessAccess(request: HttpServletRequest):Map<String, String> {
        val token = request.cookies.firstOrNull{it.name == "accessToken"}?.value
            ?:throw RuntimeException("accessToken is null")

        if(!jwtUtil.validateToken(token)){
            throw RuntimeException("accessToken is invalid")
        }

        return mapOf("accessToken" to token);
    }

}

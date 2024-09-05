package org.alham.alhamfirst.controller;


/*
 * 로그인 방식은 OAuth2.0을 사용한다.
 * JWT 토큰을 통해 API 로 통신
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

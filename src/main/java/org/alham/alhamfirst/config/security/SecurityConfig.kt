package org.alham.alhamfirst.config.security;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.filter.JwtRequestFilter
import org.alham.alhamfirst.repository.user.UserRepository
import org.alham.alhamfirst.service.CustomOAuth2UserService
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
class SecurityConfig(
    private val oAuth2SuccessHandler: CustomOAuth2SuccessHandler,
    private val jwtFilter: JwtRequestFilter,
    private val customOAuth2UserService: CustomOAuth2UserService
){

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf { it.disable() } //TODO csrf 설정 확인필요
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/login/**", "/oauth2/**","/favicon.ico","/login-success/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth ->
//                oauth.authorizationEndpoint {} //임시 엔드포인트 지정
                oauth.userInfoEndpoint {oauth2 -> oauth2.userService(customOAuth2UserService)}
                oauth.successHandler(oAuth2SuccessHandler)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() }
//            .authorizeHttpRequests { it.anyRequest().permitAll() }
//            .formLogin { it.disable() }
//            .httpBasic { it.disable() }
//
//        return http.build()
//    }

}

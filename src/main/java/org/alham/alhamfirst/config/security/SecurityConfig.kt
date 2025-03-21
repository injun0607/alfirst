package org.alham.alhamfirst.config.security;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.filter.JwtRequestFilter
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
    private val jwtFilter: JwtRequestFilter
){

    @Bean
    fun getJwtToken(): UserDTO{
        return UserDTO(
            id = CommonUtil.getEncryptedId(1L),
            name = "alham",
            age = 30,
            email = "alham@alham.net",
            userType = UserType.ADMIN
        );
    }




    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/login/**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth ->
                oauth.successHandler(oAuth2SuccessHandler)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}

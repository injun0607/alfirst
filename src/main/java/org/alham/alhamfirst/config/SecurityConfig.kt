package org.alham.alhamfirst.config;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class SecurityConfig {

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



//    private final JwtRequestFilter jwtRequestFilter;
//    private final OAuth2Service oAuth2Service;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/oauth2/**").permitAll()
//                                .anyRequest().authenticated()
//                ).sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }

}

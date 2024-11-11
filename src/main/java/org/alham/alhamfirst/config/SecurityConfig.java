package org.alham.alhamfirst.config;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.security.oauth.OAuth2Service;

//@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


//    private final JwtRequestFilter jwtRequestFilter;
    private final OAuth2Service oAuth2Service;

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

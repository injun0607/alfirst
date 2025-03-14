package org.alham.alhamfirst.config;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public UserDTO getJwtToken(){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(CommonUtil.getEncryptedId(1L));
        userDTO.setName("alham");
        userDTO.setAge(30);
        userDTO.setEmail("alham@alham.net");
        userDTO.setUserType(UserType.ADMIN);

        return userDTO;
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

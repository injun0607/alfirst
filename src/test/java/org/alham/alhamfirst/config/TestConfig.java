package org.alham.alhamfirst.config;

import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public UserDTO testUserDTO(){

        UserDTO userDTO = new UserDTO();
        userDTO.setName("alham");
        userDTO.setAge(30);
        userDTO.setEmail("alham@alham.net");
        userDTO.setUserType(UserType.ADMIN);

        return userDTO;
    }

}
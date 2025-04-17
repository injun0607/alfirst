package org.alham.alhamfirst.temp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.domain.entity.User;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitClass {


    private final UserService userService;
//

//    @PostConstruct
//    public void init(){
//        log.info("InitClass init");
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setName("alham");
//        userDTO.setAge(30);
//
//        userService.createUser(userDTO);
//
//    }


}

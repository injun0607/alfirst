package org.alham.alhamfirst.controller.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    private final UserDTO userDTO;

    @GetMapping("/users/get")
    public UserDTO getUser() {

        UserDTO jwtInfo = userDTO;

        return null;
    }

    @GetMapping("/user-info/get")
    public UserDTO getUserInfo() {
//        return userService.getUserInfo();
        return null;
    }



}

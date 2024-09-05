package org.alham.alhamfirst.controller.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/{userIdx}")
    public UserDTO getUser(@PathVariable long userIdx) {
        return userService.getUser(userIdx);
    }


}

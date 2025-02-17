package org.alham.alhamfirst.controller.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.AlhamCustomException;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    private final UserDTO userDTO;

    /**
     * 유저 기본 정보 가져오기
     * @return
     */
    @GetMapping("/users/get")
    public UserDTO getUser() {
        UserDTO jwtInfo = userDTO;
        return userService.getUserByEncryptedId(jwtInfo.getId());
    }

    /**
     * 유저 상세 정보 가져오기
     * @return
     */
    @GetMapping("/user-details/get")
    public UserDTO getUserInfo() {
//        return userService.getUserInfo();
        return null;
    }



}

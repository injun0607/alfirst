package org.alham.alhamfirst.controller.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.service.stat.UserStatService;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated()
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    private final UserStatService userStatService;

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
     * 유저 상세 정보 가져오기(유저 정보 및 상세정보)
     * @return
     */
    @GetMapping("/user-details/get")
    public UserDTO getUserInfo() {
        UserDTO jwtInfo = userDTO;

        UserDTO userInfo = userService.getUserByEncryptedId(jwtInfo.getId());
        UserStatDTO userStatInfo = userStatService.findByEncryptedId(jwtInfo.getId());
        userInfo.setStatData(userStatInfo.getStatData());
        return userInfo;
    }



}

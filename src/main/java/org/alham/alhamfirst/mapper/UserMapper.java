package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.common.error.AlhamCustomErrorLog;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.util.AESUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO createUserDTOFromEntity(User user) throws Exception {
        return UserDTO.builder()
                .id(AESUtil.encrypt(user.getId().toString()))
                .name(user.getName())
                .age(user.getAge())
                .userType(user.getUserType())
                .email(user.getEmail())
                .build();
    }


    public User createUserFromDTO(UserDTO userDTO) throws Exception {
        return User.builder()
                .id(Long.parseLong(AESUtil.decrypt(userDTO.getId())))
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .userType(userDTO.getUserType())
                .email(userDTO.getEmail())
                .build();
    }




}

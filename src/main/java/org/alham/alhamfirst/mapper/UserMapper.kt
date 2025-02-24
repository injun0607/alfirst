package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.common.error.AlhamCustomException;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.util.AESUtil;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

//    public UserDTO createUserDTOFromEntity(User user) {
//        return UserDTO.builder()
//                .id(AESUtil.encrypt(user.getId().toString()))
//                .name(user.getName())
//                .age(user.getAge())
//                .userType(user.getUserType())
//                .email(user.getEmail())
//                .build();
//    }

    fun createUserDTOFromEntity(user : User) : UserDTO{
        return UserDTO(
                id= AESUtil.encrypt(user.id.toString()),
                name = user.name,
                age = user.age,
                userType = user.userType,
                email = user.email
        )
    }


    fun createUserFromDTO(userDTO : UserDTO) : User{
        return User(
                id = userDTO.getIdDecrypt(),
                name = userDTO.name,
                age = userDTO.age,
                userType = userDTO.userType,
                email = userDTO.email
        )
    }

//    public User createUserFromDTO(UserDTO userDTO) {
//        return User.builder()
//                .id(userDTO.getIdDecrypt())
//                .name(userDTO.getName())
//                .age(userDTO.getAge())
//                .userType(userDTO.getUserType())
//                .email(userDTO.getEmail())
//                .build();
//    }




}

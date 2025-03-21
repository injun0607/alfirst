package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.domain.entity.User
import org.alham.alhamfirst.util.AESUtil

class UserMapper {

    fun createUserDTOFromEntity(user : User) : UserDTO {
        return UserDTO(
                id= AESUtil.encrypt(user.id.toString()),
                uuid = user.uuid,
                oauthProvider = user.oauthProvider,
                oauthId = user.oauthId,
                name = user.name,
                age = user.age,
                userType = user.userType,
                email = user.email
        )
    }


    fun createUserFromDTO(userDTO : UserDTO) : User {
        return User(
                id = userDTO.getIdDecrypt(),
                uuid = userDTO.uuid,
                oauthProvider = userDTO.oauthProvider,
                oauthId = userDTO.oauthId,
                name = userDTO.name,
                age = userDTO.age,
                userType = userDTO.userType,
                email = userDTO.email
        )
    }

    fun addUserStatData(userDTO: UserDTO, statData: MutableMap<String, Double>) : UserDTO {
        userDTO.statData = statData
        return userDTO
    }



}

package org.alham.alhamfirst.service.user;


import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.domain.entity.User;

interface UserService {

    fun getUser(userIdx: Long) : UserDTO
    fun createUser(userDTO: UserDTO): User
    fun getUserByIdx(userId: Long): UserDTO
    fun getUserByEncryptedId(encryptedId: String): UserDTO;


}

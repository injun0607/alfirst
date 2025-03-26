package org.alham.alhamfirst.service.user;

import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog;
import org.alham.alhamfirst.common.exception.AlhamCustomException;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.domain.entity.User;
import org.alham.alhamfirst.mapper.UserMapper;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service;

@Service
@Slf4j
class UserServiceImpl(private val userRepository: UserRepository
): UserService {

    override fun getUser(userIdx: Long): UserDTO {
        TODO();
    }

    override fun createUser(userDTO: UserDTO): User {
        val user = User(name = userDTO.name, age = userDTO.age)
        return userRepository.save(user);
    }

     override fun getUserByIdx(userId: Long): UserDTO {

        return userRepository.findById(userId)
            .map{ UserMapper().createUserDTOFromEntity(it)}
            .orElseThrow{ AlhamCustomException(HttpStatus.NOT_FOUND,"user not found") }

    }

    override fun getUserByEncryptedId(encryptedId: String): UserDTO {

        val userId = CommonUtil.getDecryptedId(encryptedId)
        return userRepository.findById(userId)
            .map{UserMapper().createUserDTOFromEntity(it)}
            .orElseThrow { AlhamCustomException(HttpStatus.NOT_FOUND,"user not found") }

    }


}

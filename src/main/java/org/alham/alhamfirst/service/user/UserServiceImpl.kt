package org.alham.alhamfirst.service.user;

import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog;
import org.alham.alhamfirst.common.exception.AlhamCustomException;
import org.alham.alhamfirst.domain.dto.user.UserDTO;
import org.alham.alhamfirst.domain.entity.User;
import org.alham.alhamfirst.mapper.UserMapper;
import org.alham.alhamfirst.repository.user.UserRepository;
import org.alham.alhamfirst.util.CommonUtil;
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
        try{
            return userRepository.findById(userId)
                .map{ UserMapper().createUserDTOFromEntity(it)}
                .orElseGet{ UserDTO() }
        } catch(e: AlhamCustomException ) {
            AlhamCustomErrorLog(exception = e);
            return UserDTO()
        }
    }

    @Override
    override fun getUserByEncryptedId(encryptedId: String): UserDTO {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)

            return userRepository.findById(userId)
                .map{UserMapper().createUserDTOFromEntity(it)}
                .orElseGet{ UserDTO() }
        } catch(e: AlhamCustomException){
            AlhamCustomErrorLog(exception = e);
            return UserDTO()
        }

    }



}

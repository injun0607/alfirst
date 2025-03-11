package org.alham.alhamfirst.service

import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.mapper.UserMapper
import org.alham.alhamfirst.service.stat.UserStatService
import org.alham.alhamfirst.service.user.UserService
import org.springframework.stereotype.Service

@Service
class OrchestratorService(
    private val userStatService: UserStatService,
    private val userService: UserService
) {
    fun getUserInfo(encryptedId: String): UserDTO {
        val userStat = userStatService.findByEncryptedId(encryptedId)
        val user = userService.getUserByEncryptedId(encryptedId)
        return UserMapper().addUserStatData(user, userStat.statData)
    }

}
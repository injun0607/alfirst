package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.domain.dto.mission.UserMissionListDTO
import org.alham.alhamfirst.repository.mission.UserMissionRepository
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service

@Service
class UserMissionService(
    private val userMissionRepository: UserMissionRepository
) {
    fun getUserMissionList(encryptedId: String): UserMissionListDTO {
        try {
            val userId = CommonUtil.getDecryptedId(encryptedId)

            userMissionRepository.getUserMissionListByUserId(userId)?.let {
                val userMissionListDTO = UserMissionListDTO()
                it.userMissionList.forEach { userMission ->
                    userMissionListDTO.addUserMissionList(userMission)
                }
                return userMissionListDTO
            } ?: throw AlhamCustomException("No User Mission Found")
        } catch (exception: Exception) {
            AlhamCustomErrorLog(errorMessage = "Error in getUserMissionList", exception = exception)
            throw AlhamCustomException("Error in getUserMissionList", exception)
        }
    }





}
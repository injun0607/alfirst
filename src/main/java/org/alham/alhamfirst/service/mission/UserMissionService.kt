package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.domain.document.mission.UserMissionDocument
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO
import org.alham.alhamfirst.domain.dto.mission.UserMissionListDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.mapper.UserMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.repository.mission.UserMissionRepository
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class UserMissionService(
    private val userMissionRepository: UserMissionRepository,
    private val missionRepository: MissionRepository
) {
    fun getTodayUserMissionList(encryptedId: String): UserMissionListDTO {
        try {
            val userId = CommonUtil.getDecryptedId(encryptedId)
            val today = LocalDate.now()

            //TODO - Redis처리하자 이거
            userMissionRepository.getUserMissionListByUserIdAndRegDate(userId,today)?.let {
                val userMissionListDTO = UserMissionListDTO()
                it.userMissionList.forEach { userMission ->
                    userMissionListDTO.addUserMissionList(userMission)
                }
                return userMissionListDTO
            }?: run {
                return registerUserMission(userId)
            }
        } catch (exception: Exception) {
            AlhamCustomErrorLog(errorMessage = "Error in getUserMissionList", exception = exception)
            throw AlhamCustomException("Error in getUserMissionList", exception)
        }
    }

    private fun registerUserMission(userId: Long): UserMissionListDTO{
        val missionList = missionRepository.getMissionList(userId).map{
            MissionMapper().createDTOFromEntity(it)
        }


        //userMission등록해야하는지 확인 필요
        //등록해야하는 경우 stat도 계산해서 같이 등록
        val todayMissionInfoList =  missionList.map{
//            missionRegister(it)
//            missionStatCaluate(it)
        }.toMutableList()

        val missionDTO = UserMissionDTO(
            userId = userId,
//            userMissionList = todayMissionInfoList,
            regDate = LocalDate.now()
        )

        //반환한다음 가져오기
        MissionMapper().createUserMissionEntityFromDTO(missionDTO).run {
//            userMissionRepository.createUserMission(it)
            return UserMissionListDTO()
        }


//        return UserMissionListDTO()
    }





}
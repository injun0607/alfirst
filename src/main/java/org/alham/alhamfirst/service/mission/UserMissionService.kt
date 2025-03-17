package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.domain.document.mission.MissionInfo
import org.alham.alhamfirst.domain.document.mission.UserMissionInfo
import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO
import org.alham.alhamfirst.domain.dto.mission.UserMissionListDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.repository.mission.UserMissionRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class UserMissionService(
    private val userMissionRepository: UserMissionRepository,
    private val missionRepository: MissionRepository,
    private val aiService: AIService
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

    //TODO - register 등록할때 필요한것 해당 미션들이 지금 등록이 필요한건지 확인이 필요하다.
    private fun registerUserMission(userId: Long): UserMissionListDTO {
        val missionList = missionRepository.getMissionList(userId).map {
            MissionMapper().createDTOFromEntity(it)
        }

        //userMission등록해야하는지 확인 필요
        //등록해야하는 경우 stat도 계산해서 같이 등록
        val todayMissionInfoList = missionList.map {
            val userMissionInfo = missionRegister(it)
            missionStatCalculate(userMissionInfo)
            userMissionInfo
        }.toMutableList()

        val userMissionDTO = UserMissionDTO(
            userId = userId,
            userMissionList = todayMissionInfoList,
            regDate = LocalDate.now()
        )

        //반환한다음 가져오기
        MissionMapper().createUserMissionEntityFromDTO(userMissionDTO).run {
            userMissionRepository.createUserMission(this)
            val userMissionList = UserMissionListDTO()

            this.userMissionList.forEach{
                userMissionList.addUserMissionList(it)
            }

            return userMissionList
        }

    }

    private fun missionRegister(missionDTO: MissionDTO): UserMissionInfo{

        val isRegister = fun(missionDTO: MissionDTO):Boolean{

            val missionInfo = missionDTO.missionInfo
            when (missionInfo.repeatedStatus){
                //평일일때
                RepeatedStatus.WEEKDAY -> return LocalDate.now().dayOfWeek.value in 1..5
                RepeatedStatus.WEEKEND -> return LocalDate.now().dayOfWeek.value in 6..7
                RepeatedStatus.DAILY -> return true
                RepeatedStatus.WEEKLY ->
                    return LocalDate.now().dayOfWeek.value == missionInfo.repeatedDay.dayValue
                RepeatedStatus.MONTHLY ->
                    return LocalDate.now().dayOfMonth == missionInfo.repeatedDate
                RepeatedStatus.SPECIFIC_DAYS ->
                    return TODO()
            }

        }

        //TODO - 미션마다 등록해야하는지 확인이 필요하다



        return UserMissionInfo(
            missionId = missionDTO.id,
            repeatedStatus = missionDTO.missionInfo.repeatedStatus,
            missionDetail = missionDTO.detail,
        )
    }

    private fun missionStatCalculate(userMissionInfo: UserMissionInfo){
        aiService.getAnswer(userMissionInfo.missionDetail).
                let{ userMissionInfo.statData = aiService.getStat(it) }
    }



}
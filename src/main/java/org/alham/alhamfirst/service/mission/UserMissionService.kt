package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
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

@Service
class UserMissionService(
    private val userMissionRepository: UserMissionRepository,
    private val missionRepository: MissionRepository,
    private val aiService: AIService
) {
    /**
     * 유저가 등록한 미션을 반환하는 함수
     */
    fun getTodayUserMissionList(encryptedId: String): UserMissionListDTO{
        try {
            val userId = CommonUtil.getDecryptedId(encryptedId)
            val today = LocalDate.now()
            val mondayOfWeek = CommonUtil.getMondayOfWeek(today)
            val firstDayOfMonth = CommonUtil.getFirstDayOfMonth(today)
            val firstWeekendDayOfWeek = CommonUtil.getFirstWeekendDayOfWeek(today)

            val userMissionList = UserMissionListDTO(
                userId = userId
            )

            //TODO - 해당부분에서 리소스 많이 안잡아먹을거라고 판단해서 corutine 사용X , 추후 병목생기면 corutine 도입해보자
            //TODO - 중복되는 패턴 디자인패턴으로 뭔가 처리가능할것같은데 추후 생각해보자
            val missionList = missionRepository.getMissionList(userId).map{
                MissionMapper().createDTOFromEntity(it)
            }

            //DailyUserMission Process
            userMissionRepository
                .getUserMissionByRepeatedStatus(userId,today,RepeatedStatus.DAILY)
                ?.let{
                    //TODO - 신규 미션이 있을수 있으니까
                    val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                    validMissionList(userMissionDTO,RepeatedStatus.DAILY,missionList)

                }?:run{
                    userMissionRegister(userId,today,missionList,RepeatedStatus.DAILY)
                }.apply{
                    userMissionList.addUserMissionList(this)
                }

            //WeeklyUserMission Process
            userMissionRepository
                .getUserMissionByRepeatedStatus(userId, mondayOfWeek,RepeatedStatus.WEEKLY)
                ?.let{
                    MissionMapper().createUserMissionDTOFromEntity(it)
                }?:run{
                    userMissionRegister(userId,mondayOfWeek,missionList,RepeatedStatus.WEEKLY)
                }.apply{
                    userMissionList.addUserMissionList(this)
                }

            //MonthlyUserMission Process
            userMissionRepository
                .getUserMissionByRepeatedStatus(userId,firstDayOfMonth,RepeatedStatus.MONTHLY)
                ?.let{
                    MissionMapper().createUserMissionDTOFromEntity(it)
                }?:run{
                    userMissionRegister(userId,firstDayOfMonth,missionList,RepeatedStatus.MONTHLY)
                }.apply{
                    userMissionList.addUserMissionList(this)
                }

            if(today.dayOfWeek.value in 1..5){
                //WeekDayUserMission Process
                userMissionRepository
                    .getUserMissionByRepeatedStatus(userId, today, RepeatedStatus.WEEKDAY)
                    ?.let{
                        MissionMapper().createUserMissionDTOFromEntity(it)
                    }?:run{
                        userMissionRegister(userId, today, missionList, RepeatedStatus.WEEKDAY)
                    }.apply{
                        userMissionList.addUserMissionList(this)
                    }
            }else{
                //WeekEndUserMission Process
                userMissionRepository
                    .getUserMissionByRepeatedStatus(userId, firstWeekendDayOfWeek, RepeatedStatus.WEEKEND)
                    ?.let {
                        MissionMapper().createUserMissionDTOFromEntity(it)
                    } ?: run {
                        userMissionRegister(userId, firstWeekendDayOfWeek, missionList, RepeatedStatus.WEEKEND)
                    }.apply {
                        userMissionList.addUserMissionList(this)
                    }
            }

            return userMissionList
        } catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in getUserMissionList", exception = exception)
            throw AlhamCustomException("Error in getUserMissionList", exception)
        }
    }

    /**
     * dailyUserMission 등록 함수
     */
    private fun userMissionRegister(userId: Long, day: LocalDate, missionList: List<MissionDTO>, repeatedStatus: RepeatedStatus): UserMissionDTO{
        val userMissionList =  missionList.filter{it.missionInfo.repeatedStatus == repeatedStatus}
            .map{
                val userMissionInfo = UserMissionInfo(
                    missionId = it.id,
                    missionDetail = it.detail
                )
                missionStatCalculate(userMissionInfo)
                userMissionInfo
            }.toMutableList()

        val userMissionDTO = UserMissionDTO(
            userId = userId,
            repeatedStatus = repeatedStatus,
            userMissionList = userMissionList,
            regDate = day
        )

        MissionMapper().createUserMissionEntityFromDTO(userMissionDTO).run{
            userMissionRepository.createUserMission(this)
        }

        return userMissionDTO
    }
    private fun missionStatCalculate(userMissionInfo: UserMissionInfo){
        aiService.getAnswer(userMissionInfo.missionDetail).
                let{ userMissionInfo.statData = aiService.getStat(it) }
    }

    /**
     * 신규 등록된 미션이 기존 userMission 포함되어있는지 확인 하는 함수
     */
    private fun validMissionList(userMission: UserMissionDTO, repeatedStatus: RepeatedStatus, missionList: List<MissionDTO>){

        //받아온 미션리스트
        val filteredMissionIdList = missionList
            .filter{ it.missionInfo.repeatedStatus == repeatedStatus }

        //등록된 유저 미션
        val createdMissionId = userMission.userMissionList.map{it.missionId}.toSet()

        filteredMissionIdList.filter { !createdMissionId.contains(it.id) }
            .map{
                val userMissionInfo = UserMissionInfo(
                    missionId = it.id,
                    missionDetail = it.detail
                )
                missionStatCalculate(userMissionInfo)
                userMissionInfo
            }.apply{
                userMission.userMissionList.addAll(this)
            }

    }

}
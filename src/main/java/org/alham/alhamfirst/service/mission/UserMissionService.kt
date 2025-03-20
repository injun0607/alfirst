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
import org.alham.alhamfirst.repository.stat.UserStatRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserMissionService(
    private val userMissionRepository: UserMissionRepository,
    private val missionRepository: MissionRepository,
    private val userStatRepository: UserStatRepository,
    private val aiService: AIService
) {
    /**
     * 유저가 등록한 미션을 반환하는 함수
     */
    //TODO - 신규로 만들때 userMission
    fun getTodayUserMissionList(encryptedId: String): UserMissionListDTO{
        try {
            val userId = CommonUtil.getDecryptedId(encryptedId)
            val today = LocalDate.now()
            val mondayOfWeek = CommonUtil.getMondayOfWeek(today)
            val firstDayOfMonth = CommonUtil.getFirstDayOfMonth(today)
            val firstWeekendDayOfWeek = CommonUtil.getFirstWeekendDayOfWeek(today)

            var userMissionList = UserMissionListDTO(
                userId = userId
            )

            //TODO - 해당부분에서 리소스 많이 안잡아먹을거라고 판단해서 corutine 사용X , 추후 병목생기면 corutine 도입해보자
            //TODO - 중복되는 패턴 디자인패턴으로 뭔가 처리가능할것같은데 추후 생각해보자
            val missionList = missionRepository.getMissionList(userId).map{
                MissionMapper().createDTOFromEntity(it)
            }

            //DailyUserMission Process
            val dailyUserMission =userMissionRepository
                .getUserMissionByRepeatedStatus(userId,today,RepeatedStatus.DAILY)
                ?.let{
                    val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                    validMissionList(userMissionDTO,RepeatedStatus.DAILY,missionList)
                }?:run{
                    userMissionRegister(userId,today,missionList,RepeatedStatus.DAILY)
                }

            userMissionList.addUserMissionList(dailyUserMission)

            //WeeklyUserMission Process
            val weeklyUserMission = userMissionRepository
                .getUserMissionByRepeatedStatus(userId, mondayOfWeek, RepeatedStatus.WEEKLY)
                ?.let {
                    val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                    validMissionList(userMissionDTO, RepeatedStatus.WEEKLY, missionList)
                } ?: run {
                    userMissionRegister(userId, mondayOfWeek, missionList, RepeatedStatus.WEEKLY)
                }

            userMissionList.addUserMissionList(weeklyUserMission)

            //MonthlyUserMission Process
            val monthlyUserMission = userMissionRepository
                .getUserMissionByRepeatedStatus(userId, firstDayOfMonth, RepeatedStatus.MONTHLY)
                ?.let {
                    val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                    validMissionList(userMissionDTO, RepeatedStatus.MONTHLY, missionList)
                } ?: run {
                    userMissionRegister(userId, firstDayOfMonth, missionList, RepeatedStatus.MONTHLY)
                }

            userMissionList.addUserMissionList(monthlyUserMission)

            if(today.dayOfWeek.value in 1..5){
                //WeekDayUserMission Process
                val weekDayUserMission = userMissionRepository
                    .getUserMissionByRepeatedStatus(userId, today, RepeatedStatus.WEEKDAY)
                    ?.let {
                        val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                        validMissionList(userMissionDTO, RepeatedStatus.WEEKDAY, missionList)
                    } ?: run {
                        userMissionRegister(userId, today, missionList, RepeatedStatus.WEEKDAY)
                    }

                userMissionList.addUserMissionList(weekDayUserMission)

            }else{
                //WeekEndUserMission Process
                val weekendUserMission = userMissionRepository
                    .getUserMissionByRepeatedStatus(userId, firstWeekendDayOfWeek, RepeatedStatus.WEEKEND)
                    ?.let {
                        val userMissionDTO = MissionMapper().createUserMissionDTOFromEntity(it)
                        validMissionList(userMissionDTO, RepeatedStatus.WEEKEND, missionList)
                    } ?: run {
                        userMissionRegister(userId, firstWeekendDayOfWeek, missionList, RepeatedStatus.WEEKEND)
                    }

                userMissionList.addUserMissionList(weekendUserMission)
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
            val createUserMission = userMissionRepository.createUserMission(this)
            return MissionMapper().createUserMissionDTOFromEntity(createUserMission)
        }

    }
    private fun missionStatCalculate(userMissionInfo: UserMissionInfo){
        aiService.getAnswer(userMissionInfo.missionDetail).
                let{ userMissionInfo.statData = aiService.getStat(it) }
    }

    /**
     * 신규 등록된 미션이 기존 userMission 포함되어있는지 확인 하는 함수
     */
    private fun validMissionList(userMission: UserMissionDTO, repeatedStatus: RepeatedStatus, missionList: List<MissionDTO>): UserMissionDTO {

        //받아온 미션리스트
        val filteredMissionIdList = missionList
            .filter { it.missionInfo.repeatedStatus == repeatedStatus }

        //등록된 유저 미션
        val createdMissionId = userMission.userMissionList.map { it.missionId }.toSet()

        val newMission = filteredMissionIdList.filter { !createdMissionId.contains(it.id) }
            .map {
                val userMissionInfo = UserMissionInfo(
                    missionId = it.id,
                    missionDetail = it.detail
                )
                missionStatCalculate(userMissionInfo)
                userMissionInfo
            }

        if(newMission.isNotEmpty()) {
            userMission.userMissionList.addAll(newMission)
            userMissionRepository.updateUserMissionList(MissionMapper().createUserMissionEntityFromDTO(userMission))
        }

        return userMission
    }


    fun completeUserMission(encryptedId: String, userMissionId: String, missionId: String, complete: Boolean): Boolean{

        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            //TODO - complete미션시 수행해야하는것
            /*
            1. userMissionInfo에서 해당 미션 complete 값 변경
            2. 해당 stat 뽑아서 userStat에 업데이트
            3. 해당 mission에 streak 값 변경(+1), lastEndDate 변경
             */

            //미션 완료값 검증
            userMissionRepository.getUserMission(userMissionId,userId)?.let{
                if(it.userMissionList.find{it.missionId == missionId}?.missionComplete == complete){
                    return false
                }
            }?: throw Exception("UserMission not found")

            val userMissionDTO = userMissionRepository.completeUserMission(userId, userMissionId, missionId, complete)
                ?.let{
                    MissionMapper().createUserMissionDTOFromEntity(it)
                }?:throw Exception("UserMission not competed")

            val statData = userMissionDTO.userMissionList.find{it.missionId == missionId}?.statData
                ?: throw Exception("StatData not found")

            val mission = missionRepository.getMission(missionId,userId)?.let{MissionMapper().createDTOFromEntity(it)}
                ?: throw Exception("Mission not found")

            mission.apply{
                val streak = this.streak + 1;
                if(streak > mission.maxStreak){
                    mission.maxStreak = streak
                }
                this.streak = streak
                this.lastEndDate = LocalDate.now()
            }

            //userStat 업데이트
            userStatRepository.updateUserStat(userId,statData,complete)
            //mission 정보 업데이트
            missionRepository.updateStreakAndLastEndDate(MissionMapper().createEntityFromDTO(mission))

            return true
        }catch (exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in completeUserMission", exception = exception)
            throw AlhamCustomException("Error in completeUserMission", exception)
        }


    }

}
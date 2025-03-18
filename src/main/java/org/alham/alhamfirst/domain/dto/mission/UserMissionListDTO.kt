package org.alham.alhamfirst.domain.dto.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus


class UserMissionListDTO (
    var userId: Long? = null,
    var weekDayMission: UserMissionDTO = UserMissionDTO(),
    var weekEndMission: UserMissionDTO = UserMissionDTO(),
    var dailyMission: UserMissionDTO = UserMissionDTO(),
    var weeklyMission: UserMissionDTO = UserMissionDTO(),
    var monthlyMission: UserMissionDTO = UserMissionDTO(),
    var specificDayMission: UserMissionDTO = UserMissionDTO(),
){
    fun addUserMissionList(userMission: UserMissionDTO){
        when(userMission.repeatedStatus){
            RepeatedStatus.WEEKDAY -> weekDayMission
            RepeatedStatus.WEEKEND-> weekEndMission
            RepeatedStatus.DAILY -> dailyMission
            RepeatedStatus.WEEKLY -> weeklyMission
            RepeatedStatus.MONTHLY -> monthlyMission
            RepeatedStatus.SPECIFIC_DAYS -> specificDayMission
        }
    }


}
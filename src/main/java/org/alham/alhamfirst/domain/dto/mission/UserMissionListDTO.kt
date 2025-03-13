package org.alham.alhamfirst.domain.dto.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.domain.document.mission.UserMissionInfo


class UserMissionListDTO (
    var id: String? = null,
    var userId: Long? = null,
    var weekDayMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var weekEndMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var dailyMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var weeklyMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var monthlyMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var specificDayMissionList: MutableList<UserMissionInfo> = mutableListOf()
){
    fun addUserMissionList(userMissionInfo: UserMissionInfo){
        when(userMissionInfo.repeatedStatus){
            RepeatedStatus.WEEKDAY -> weekDayMissionList.add(userMissionInfo)
            RepeatedStatus.WEEKEND-> weekEndMissionList.add(userMissionInfo)
            RepeatedStatus.DAILY -> dailyMissionList.add(userMissionInfo)
            RepeatedStatus.WEEKLY -> weeklyMissionList.add(userMissionInfo)
            RepeatedStatus.MONTHLY -> monthlyMissionList.add(userMissionInfo)
            RepeatedStatus.SPECIFIC_DAYS -> specificDayMissionList.add(userMissionInfo)
        }
    }


}
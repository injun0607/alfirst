package org.alham.alhamfirst.domain.dto.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.domain.document.mission.UserMissionInfo
import java.time.LocalDate

/**
 * UserMissionDTO
 * @id : UserMission Id
 * @UserId : User Id
 * @repeatedStatus : Repeated Status
 * @userMissionList : UserMissionInfo List
 *  - @missionId : Mission Id
 *  - @missionDetail : Mission Detail
 *  - @missionComplete : Mission Complete
 *  - @statData : Stat Data
 *  - @completeDateTime : Complete Date Time
 * @regDate : Register Date
 */
data class UserMissionDTO(
    var id: String? = null,
    var userId: Long? = null,
    var repeatedStatus: RepeatedStatus = RepeatedStatus.DAILY,
    var userMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var regDate: LocalDate? = LocalDate.now()
) {}
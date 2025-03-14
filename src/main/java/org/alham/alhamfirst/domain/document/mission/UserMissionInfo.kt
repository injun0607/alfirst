package org.alham.alhamfirst.domain.document.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import java.time.LocalDateTime

class UserMissionInfo(
    var missionId: String? = null,
    var repeatedStatus: RepeatedStatus = RepeatedStatus.DAILY,
    var missionDetail: String = "",
    var missionComplete: Boolean = false,
    var statData: Map<String, String> = mapOf(),
    var completeDateTime : LocalDateTime? = null
) {

}
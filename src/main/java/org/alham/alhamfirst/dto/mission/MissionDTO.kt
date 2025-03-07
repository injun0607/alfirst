package org.alham.alhamfirst.dto.mission

import org.alham.alhamfirst.common.enums.DayStatus
import org.alham.alhamfirst.common.enums.RepeatedStatus

data class MissionDTO(
    var id : Long? = null,
    var userId : Long? = null,
    var detail: String = "",
    var repeatedStatus: RepeatedStatus = RepeatedStatus.DAILY,
    var dayStatus: DayStatus = DayStatus.NONE
) {

}
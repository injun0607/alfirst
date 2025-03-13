package org.alham.alhamfirst.domain.document.mission

import org.alham.alhamfirst.common.enums.DayStatus
import org.alham.alhamfirst.common.enums.RepeatedStatus

class MissionInfo(
    var repeatedStatus: RepeatedStatus = RepeatedStatus.DAILY,
    var repeatedDay: DayStatus = DayStatus.NONE,
    var repeatedDate: Int = 0,
    var repeatedInterval: Int = 0
) {
}
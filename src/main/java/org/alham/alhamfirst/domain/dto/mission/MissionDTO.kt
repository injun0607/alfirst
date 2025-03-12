package org.alham.alhamfirst.domain.dto.mission

import org.alham.alhamfirst.domain.document.mission.MissionStatus
import java.time.LocalDateTime

data class MissionDTO(
    var id : String? = null,
    var userId : Long? = null,
    var detail: String = "",
    var missionStatus: MissionStatus = MissionStatus(),
    var streak: Int = 0,
    var maxStreak: Int = 0,
    var regDate: LocalDateTime = LocalDateTime.now()
) {

}
package org.alham.alhamfirst.domain.dto.mission

import org.alham.alhamfirst.domain.document.mission.UserMissionInfo
import java.time.LocalDate

data class UserMissionDTO(
    var id: String? = null,
    var userId: Long? = null,
    var userMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var regDate: LocalDate? = LocalDate.now()
) {}
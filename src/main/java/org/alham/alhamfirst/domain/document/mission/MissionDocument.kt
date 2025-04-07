package org.alham.alhamfirst.domain.document.mission

import jakarta.persistence.*
import org.alham.alhamfirst.common.enums.Intensity
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document
class MissionDocument(

    @Id
    val id : String? = null,
    var userId : Long? = null,
    var detail: String = "",
    var missionInfo: MissionInfo = MissionInfo(),
    var streak: Int = 0,
    var maxStreak: Int = 0,
    var regDate: LocalDateTime = LocalDateTime.now(),
    var lastEndDate: LocalDate? = null,
    var useFlag: Boolean = true,
    var intensity: Intensity = Intensity.LOW,
    var updateIntensityFlag: Boolean = false,
) {



}
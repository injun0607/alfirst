package org.alham.alhamfirst.domain.document.mission

import jakarta.persistence.*
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class MissionDocument(

    @Id
    val id : Long? = null,
    var userId : Long? = null,
    var detail: String = "",
    var missionStatus: MissionStatus = MissionStatus(),
    var streak: Int = 0,
    var maxStreak: Int = 0,
    var regDate: LocalDateTime = LocalDateTime.now()
) {



}
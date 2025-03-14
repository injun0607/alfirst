package org.alham.alhamfirst.domain.document.mission

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class UserMissionDocument(
    @Id
    var id: String? = null,
    var userId: Long? = null,
    var userMissionList: MutableList<UserMissionInfo> = mutableListOf(),
    var regDate: LocalDate? = LocalDate.now()
){}
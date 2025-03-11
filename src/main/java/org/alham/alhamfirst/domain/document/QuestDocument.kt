package org.alham.alhamfirst.domain.document

import jakarta.persistence.Id
import org.alham.alhamfirst.common.Constant
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document("quest")
class QuestDocument(
        @Id
        var id: String? = null,
        var userId: Long = 0,
        var detail: String,
        var completed: Boolean,
        var statData: MutableMap<String, Double>,
        var startDate: LocalDateTime = LocalDateTime.now(),
        var endDate: LocalDateTime? = null
) {


}
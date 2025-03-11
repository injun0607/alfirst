package org.alham.alhamfirst.domain.dto.quest;

import org.alham.alhamfirst.common.Constant
import java.time.LocalDateTime

data class QuestDTO(
    var id: String? = null,
    var userId: Long = 0,
    var detail: String = "",
    var completed: Boolean = false,
    var statData: MutableMap<String, Double> = mutableMapOf(),
    var startDate: LocalDateTime = LocalDateTime.now(),
    var endDate: LocalDateTime? = null
) {

}

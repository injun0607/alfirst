package org.alham.alhamfirst.dto.quest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * front 단에서 보여주는 quest 정보
 * todo와 stat이 들어가있다.
 */

data class QuestDTO(
    var id: Long? = null,
    var detail: String,
    var completed: Boolean,
    var statData: MutableMap<String, Double>
) {}

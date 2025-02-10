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

@Getter
@Setter
@NoArgsConstructor
public class QuestDTO {
    private Long id;
    private String detail;
    private boolean completed;
    private Map<String, Double> statData = new HashMap<>();

    @Builder
    public QuestDTO (Long id, String detail, boolean completed, Map<String, Double> statData) {
        this.id = id;
        this.detail = detail;
        this.completed = completed;
        this.statData = statData;
    }

}

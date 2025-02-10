package org.alham.alhamfirst.dto.stat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class StatDTO {
    private String id;
    private Long todoIdx;
    private Map<String, Double> statData = new HashMap<>();

    @Builder
    public StatDTO(String id, Long todoIdx, Map<String, Double> statData) {
        this.id = id;
        this.todoIdx = todoIdx;
        this.statData = statData;
    }

}

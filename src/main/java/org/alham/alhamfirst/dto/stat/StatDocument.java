package org.alham.alhamfirst.dto.stat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "stat")
@Getter
@Setter
@NoArgsConstructor
public class StatDocument {

    @Id
    private String id;
    private Long todoIdx;
    private Map<String,Integer> statData;

    public StatDocument(Long todoIdx, Map<String, Integer> statData) {
        this.todoIdx = todoIdx;
        this.statData = statData;
    }


}

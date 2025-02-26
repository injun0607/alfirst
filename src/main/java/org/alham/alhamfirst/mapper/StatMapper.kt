package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.springframework.stereotype.Component;

@Component
public class StatMapper {

    public StatDTO createStatDTOFromDocument(StatDocument statDocument){
        return StatDTO.builder()
                .id(statDocument.getId())
                .todoIdx(statDocument.getTodoIdx())
                .statData(statDocument.getStatData())
                .build();
    }
}

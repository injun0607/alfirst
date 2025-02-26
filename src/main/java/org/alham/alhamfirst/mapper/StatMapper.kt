package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.springframework.stereotype.Component;

@Component
class StatMapper {

    fun createStatDTOFromDocument(statDocument: StatDocument): StatDTO{
        return StatDTO(
                id = statDocument.id,
                todoIdx = statDocument.todoIdx,
                statData = statDocument.statData
        )
    }

}

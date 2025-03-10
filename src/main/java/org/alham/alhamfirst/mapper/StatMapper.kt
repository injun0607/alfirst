package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.domain.document.stat.StatDocument;
import org.alham.alhamfirst.domain.dto.stat.StatDTO;
import org.springframework.stereotype.Component;

class StatMapper {

    fun createStatDTOFromDocument(statDocument: StatDocument): StatDTO {
        return StatDTO(
                id = statDocument.id,
                todoIdx = statDocument.todoIdx,
                statData = statDocument.statData
        )
    }

}

package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.domain.document.QuestDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO


class QuestMapper() {

     fun createQuestDTOFromDocument(questDocument: QuestDocument): QuestDTO {
        return QuestDTO(
            id = questDocument.id,
            userId = questDocument.userId,
            detail = questDocument.detail,
            completed = questDocument.completed,
            statData = questDocument.statData,
            startDate = questDocument.startDate,
            endDate = questDocument.endDate
        )
     }

    fun createDocumentFromDTO(questDTO: QuestDTO): QuestDocument {
        return QuestDocument(
            id = questDTO.id,
            userId = questDTO.userId,
            detail = questDTO.detail,
            completed = questDTO.completed,
            statData = questDTO.statData,
            startDate = questDTO.startDate,
            endDate = questDTO.endDate
        )
    }
}

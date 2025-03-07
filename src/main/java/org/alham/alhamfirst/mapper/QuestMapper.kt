package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.springframework.stereotype.Component;


@Component
class QuestMapper() {

     fun createQuestDTO(todoDTO: TodoDTO,statDTO: StatDTO ): QuestDTO{
        return QuestDTO(id = todoDTO.id, detail = todoDTO.detail, completed = todoDTO.completed, statData = statDTO.statData)
     }

    fun createQuestListDTO(todoDTOList: List<TodoDTO>, statDTOList: List<StatDTO>): List<QuestDTO> {
        return todoDTOList.map{ todoDTO->
            val statDTO = statDTOList.find{statDTO -> todoDTO.id == statDTO.todoIdx} ?: StatDTO()
            QuestDTO(id = todoDTO.id, detail = todoDTO.detail, completed = todoDTO.completed, statData = statDTO.statData)
        }
    }
}

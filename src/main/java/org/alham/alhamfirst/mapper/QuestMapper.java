package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestMapper {

    public QuestDTO createQuestDTO(TodoDTO todoDTO , StatDTO statDTO) {
        return QuestDTO.builder()
                .id(todoDTO.getId())
                .detail(todoDTO.getDetail())
                .completed(todoDTO.getCompleted())
                .statData(statDTO.getStatData())
                .build();
    }

    public List<QuestDTO> createQuestListDTO(List<TodoDTO> todoDTOList , List<StatDTO> statDTOList) {
        return todoDTOList.stream().map(todoDTO -> {
            StatDTO statDTO = statDTOList.stream().filter(stat -> stat.getTodoIdx().equals(todoDTO.getId())).findFirst().orElse(new StatDTO());
            return QuestDTO.builder()
                    .id(todoDTO.getId())
                    .detail(todoDTO.getDetail())
                    .completed(todoDTO.getCompleted())
                    .statData(statDTO.getStatData())
                    .build();
        }).toList();
    }

}

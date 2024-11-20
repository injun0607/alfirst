package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo createTodoFromDTO(TodoDTO todoDTO) {
        return Todo.builder()
                .detail(todoDTO.getDetail())
                .completed(todoDTO.isCompleted())
                .startDate(todoDTO.getStartDate())
                .build();
    }

    public TodoDTO createTodoDTOFromEntity(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .detail(todo.getDetail())
                .completed(todo.isCompleted())
                .build();
    }

    /**
     * userId를 포함한 TodoDTO 생성
     * @param todo
     * @return
     */
    public TodoDTO createTodoDTOWithUserIdFromEntity(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .userId(todo.getUser().getId())
                .detail(todo.getDetail())
                .completed(todo.isCompleted())
                .build();
    }




    public void updateTodoFromDTO(Todo todo, TodoDTO todoDTO) {
        todo.updateTodo(todoDTO.getDetail());
    }
}

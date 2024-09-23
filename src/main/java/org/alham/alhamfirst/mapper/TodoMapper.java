package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo createTodoFromDTO(TodoDTO todoDTO) {
        return Todo.builder()
                .title(todoDTO.getTitle())
                .detail(todoDTO.getDetail())
                .build();
    }

    public TodoDTO createTodoDTOFromEntity(Todo todo) {
        return TodoDTO.builder()
                .title(todo.getTitle())
                .detail(todo.getDetail())
                .completed(todo.isCompleted())
                .build();
    }
}

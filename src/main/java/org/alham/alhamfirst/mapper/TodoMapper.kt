package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;
import org.springframework.stereotype.Component;

@Component
class TodoMapper {

//    public Todo createTodoFromDTO(TodoDTO todoDTO) {
//        return Todo.builder()
//                .detail(todoDTO.getDetail())
//                .completed(todoDTO.getCompleted())
//                .startDate(todoDTO.getStartDate())
//                .build();
//    }

    fun createTodoFromDTO(todoDTO: TodoDTO) :Todo {
        return Todo(
            detail=todoDTO.detail,
            completed = todoDTO.completed,
            startDate = todoDTO.startDate
        );
    }

//    public TodoDTO createTodoDTOFromEntity(Todo todo) {
//        return TodoDTO.()
//                .id(todo.getId())
//                .detail(todo.getDetail())
//                .completed(todo.isCompleted())
//                .build();
//    }

    fun createTodoDTOFromEntity(todo : Todo): TodoDTO {
        return TodoDTO(
            id = todo.id,
            detail = todo.detail,
            completed = todo.completed,
        )
    }

    /**
     * userId를 포함한 TodoDTO 생성
     * @param todo
     * @return
     */
//    public TodoDTO createTodoDTOWithUserIdFromEntity(Todo todo) {
//        return TodoDTO.builder()
//                .id(todo.getId())
//                .userId(todo.getUser().getId())
//                .detail(todo.getDetail())
//                .completed(todo.isCompleted())
//                .build();
//    }

    fun createTodoDTOWithUserIdFromEntity(todo : Todo): TodoDTO {
        return TodoDTO(
            id = todo.id,
            userId = todo.user?.id,
            detail = todo.detail,
            completed = todo.completed
        )
    }


    fun updateTodoFromDTO(todo : Todo, todoDTO : TodoDTO) {
        todo.updateTodo(
            todoDTO.detail
        )
    }


//    public void updateTodoFromDTO(Todo todo, TodoDTO todoDTO) {
//        todo.updateTodo(todoDTO.getDetail());
//    }
}

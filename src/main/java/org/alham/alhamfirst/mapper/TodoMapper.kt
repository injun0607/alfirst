package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.domain.dto.todo.TodoDTO
import org.alham.alhamfirst.domain.entity.todo.Todo

class TodoMapper {


    fun createTodoFromDTO(todoDTO: TodoDTO) : Todo {
        return Todo(
            detail=todoDTO.detail,
            completed = todoDTO.completed,
            startDate = todoDTO.startDate
        );
    }

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

}

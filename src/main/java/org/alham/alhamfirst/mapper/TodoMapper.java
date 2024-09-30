package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.Todo;
import org.alham.alhamfirst.entity.TodoList;
import org.alham.alhamfirst.entity.User;
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
                .id(todo.getId())
                .title(todo.getTitle())
                .detail(todo.getDetail())
                .completed(todo.isCompleted())
                .build();
    }


    public TodoList createTodoListFromDTO(TodoListDTO todoListDTO) {

        User user = User.createTempUser(todoListDTO.getUserId());
        TodoList todoList = new TodoList(todoListDTO.getDate());
        todoList.addUser(user);

        if(!todoListDTO.getTodoList().isEmpty()){
            for(TodoDTO todoDTO : todoListDTO.getTodoList()){
                Todo todo = createTodoFromDTO(todoDTO);
                todoList.addTodo(todo);
            }
        }

        return todoList;
    }

    public TodoListDTO createTodoListDTOFromEntity(TodoList todoList) {
        TodoListDTO todoListDTO = TodoListDTO.builder()
                .id(todoList.getId())
                .date(todoList.getDate())
                .build();

        if(!todoList.getTodoList().isEmpty()){
            for(Todo todo : todoList.getTodoList()){
                TodoDTO todoDTO = createTodoDTOFromEntity(todo);
                todoListDTO.getTodoList().add(todoDTO);
            }
        }

        return todoListDTO;
    }


    public void updateTodoFromDTO(Todo todo, TodoDTO todoDTO) {
        todo.updateTodo(todoDTO.getTitle(), todoDTO.getDetail(), todoDTO.isCompleted());
    }
}

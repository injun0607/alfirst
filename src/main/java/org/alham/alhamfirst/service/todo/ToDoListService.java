package org.alham.alhamfirst.service.todo;

import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.TodoList;

import java.time.LocalDate;
import java.util.List;

public interface ToDoListService {

    public void createTodoList(TodoListDTO todoListDTO);

    public void updateTodoList(TodoListDTO todoListDTO);

    public TodoList getTodoListByToday(long userId);
    public TodoListDTO getTodoListByDate(long userId, LocalDate date);

    public List<TodoListDTO> getAllTodoListByUserId(long userId);

}

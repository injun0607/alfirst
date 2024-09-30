package org.alham.alhamfirst.service.todo;

import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.todo.TodoList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ToDoListService {

    public void createTodoList(TodoListDTO todoListDTO);

    public void updateTodoList(TodoListDTO todoListDTO);

    public TodoList getTodoListByToday(long userId);
    public Optional<TodoListDTO> getTodoListByDate(long userId, LocalDate date);

    public List<TodoListDTO> getAllTodoListByUserId(long userId);

}

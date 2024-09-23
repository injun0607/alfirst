package org.alham.alhamfirst.service.todo;

import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.TodoList;

public interface ToDoListService {

    public void createTodoList(long userId);

    public void updateTodoList(long userId, TodoListDTO todoList);

    public TodoList getTodoListByToday(long userId);
    public TodoList getTodoListByDate(long userId, String date);

}

package org.alham.alhamfirst.service.orchestrator.todo;


import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;

import java.util.List;


public interface TodoService {



    public Todo createTodo(TodoDTO todoDTO);

    List<TodoDTO> getTodoList();

    List<TodoDTO> listTodo();

    TodoDTO getTodoDetail(Long id);

    void updateTodoDetail(Long id, TodoDTO todoDTO);


}

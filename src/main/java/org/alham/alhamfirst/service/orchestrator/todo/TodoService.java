package org.alham.alhamfirst.service.orchestrator.todo;


import org.alham.alhamfirst.dto.todo.TodoDTO;

import java.util.List;


public interface TodoService {



    public void createTodo(TodoDTO todoDTO);

    List<TodoDTO> getTodoList();

    List<TodoDTO> listTodo();

    TodoDTO getTodoDetail(Long id);

    void updateTodoDetail(Long id, TodoDTO todoDTO);


}

package org.alham.alhamfirst.service.todo;


import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    public void createTodo(TodoDTO todoDTO);

    List<TodoDTO> listTodo();

    TodoDTO getTodoDetail(Long id);

    void updateTodoDetail(Long id, TodoDTO todoDTO);
}

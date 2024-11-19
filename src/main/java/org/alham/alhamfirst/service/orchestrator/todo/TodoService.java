package org.alham.alhamfirst.service.orchestrator.todo;


import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;

import java.util.List;


public interface TodoService {



    public Todo createTodo(TodoDTO todoDTO);

    List<TodoDTO> getTodoList();

    List<TodoDTO> listTodo();

    TodoDTO getTodoDetail(Long id);

    TodoDTO getTodoDetailByUserId(Long id);

    List<TodoDTO> getTodoListByUserIdWithUndo(Long id);

    void updateTodoDetail(Long id, TodoDTO todoDTO);

    void deleteTodo(long id);

    //stat 저장 로직이 실패하면 적용할 로직
    void deleteTodoWithStatReward(long id);

}

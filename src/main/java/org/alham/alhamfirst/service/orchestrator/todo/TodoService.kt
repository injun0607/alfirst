package org.alham.alhamfirst.service.orchestrator.todo;


import org.alham.alhamfirst.domain.dto.todo.TodoDTO;
import org.alham.alhamfirst.domain.entity.todo.Todo;


public interface TodoService {

    fun createTodo(todoDTO : TodoDTO) : Todo
    fun getTodoList(): List<TodoDTO>
    fun getTodoByIdAndUserId(todoId: Long, userId: Long): Todo
    fun getTodoListByUserIdWithUnCompleted(id : Long) : List<TodoDTO>
    fun completeTodo(todoDTO : TodoDTO) : TodoDTO
    fun deleteTodo(id : Long);
    fun deleteTodoWIthStartReward(id : Long);

}

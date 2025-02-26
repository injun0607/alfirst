package org.alham.alhamfirst.service.orchestrator.todo;


import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;


public interface TodoService {



//    public Todo createTodo(TodoDTO todoDTO);
    fun createTodo(todoDTO : TodoDTO) : Todo

//    List<TodoDTO> getTodoList();
    fun getTodoList(): List<TodoDTO>

//    List<TodoDTO> listTodo();
    fun listTodo():List<TodoDTO>

//    TodoDTO getTodoDetail(Long id);
    fun getTodoDetail(id:Long) : TodoDTO

//    TodoDTO getTodoDetailByUserId(Long id);
    fun getTodoDetailByUserId(id : Long) : TodoDTO

//    List<TodoDTO> getTodoListByUserIdWithUndo(Long id);
    fun getTodoListByUserIdWithUndo(id : Long) : List<TodoDTO>

//    TodoDTO updateTodoDetail(TodoDTO todoDTO);
    fun updateTodoDetail(todoDTO : TodoDTO) : TodoDTO

//    void deleteTodo(long id);

    fun deleteTodo(id : Long);

    //stat 저장 로직이 실패하면 적용할 로직
//    void deleteTodoWithStatReward(long id);

    fun deleteTodoWIthStartReward(id : Long);

}

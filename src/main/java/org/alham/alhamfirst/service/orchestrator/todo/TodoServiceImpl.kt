package org.alham.alhamfirst.service.orchestrator.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.entity.todo.Todo;
import org.alham.alhamfirst.mapper.TodoMapper;
import org.alham.alhamfirst.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Slf4j
@Transactional(readOnly = true)
class TodoServiceImpl(private val todoRepository : TodoRepository ,
                      private val todoMapper: TodoMapper
) : TodoService{

    override fun getTodoDetailByUserId(id: Long): TodoDTO {
        val byUserId = todoRepository.findByUserId(id)
        return todoMapper.createTodoDTOFromEntity(byUserId)

    }

    /**
     * 할일 생성
     * startDate는 해당 레이어에서 넣어줌
     *
     * @param todoDTO
     * @return
     */
    @Transactional
    override fun createTodo(todoDTO : TodoDTO) : Todo{
        todoDTO.startDate = LocalDate.now()
        val todo = todoMapper.createTodoFromDTO(todoDTO)
        val user = User().createTempUser(todoDTO.userId?:0);
        todo.addUser(user)
        return todoRepository.save(todo);
    }

    override fun getTodoList():List<TodoDTO>{
        return todoRepository.findAll().stream().map(todoMapper::createTodoDTOFromEntity).toList()
    }

    override fun listTodo():List<TodoDTO>{
        return todoRepository.findAll().stream().map(todoMapper::createTodoDTOFromEntity).toList()
    }

    override fun getTodoDetail(id: Long): TodoDTO {
        return todoRepository.findById(id).map(todoMapper::createTodoDTOFromEntity).orElse(TodoDTO())
    }
    @Transactional
    override fun updateTodoDetail(todoDTO: TodoDTO): TodoDTO {
        val todo = todoRepository.findById(todoDTO.id?:0).orElseThrow();
        todo.updateCompeted(todoDTO.completed)
        val saved = todoRepository.save(todo)
        return todoMapper.createTodoDTOWithUserIdFromEntity(saved)
    }

    override fun getTodoListByUserIdWithUndo(id : Long) : List<TodoDTO>{
        return todoRepository.findUndoListByUserId(id).stream().map(todoMapper::createTodoDTOFromEntity).toList()
    }

    override fun deleteTodo(id: Long){
        todoRepository.deleteById(id)
    }

    override fun deleteTodoWIthStartReward(id: Long) {
        todoRepository.deleteById(id)
    }

}

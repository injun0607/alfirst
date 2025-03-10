package org.alham.alhamfirst.service.orchestrator.todo;

import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.exception.MariaDBCustomException
import org.alham.alhamfirst.domain.dto.todo.TodoDTO;
import org.alham.alhamfirst.domain.entity.User;
import org.alham.alhamfirst.domain.entity.todo.Todo;
import org.alham.alhamfirst.mapper.TodoMapper;
import org.alham.alhamfirst.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Slf4j
@Transactional(readOnly = true)
class TodoServiceImpl(private val todoRepository : TodoRepository
) : TodoService{

    /**
     * 할일 생성
     * startDate는 해당 레이어에서 넣어줌
     *
     * @param todoDTO
     * @return
     */
    @Transactional
    override fun createTodo(todoDTO : TodoDTO) : Todo {
        todoDTO.startDate = LocalDate.now()
        val todo = TodoMapper().createTodoFromDTO(todoDTO)
        val user = User().createTempUser(todoDTO.userId?:0);
        todo.addUser(user)
        return todoRepository.save(todo)
    }

    override fun getTodoList():List<TodoDTO>{
        return todoRepository.findAll().map { TodoMapper().createTodoDTOFromEntity(it) }
    }

    override fun getTodoByIdAndUserId(todoId: Long, userId: Long): Todo {
        return todoRepository.findTodoByIdAndUserId(todoId, userId)?:throw MariaDBCustomException("Todo not found")
    }
    @Transactional
    override fun completeTodo(todoDTO: TodoDTO): TodoDTO {
        val todo = todoRepository.findById(todoDTO.id?:0).orElseThrow{MariaDBCustomException("Todo not found")}
        todo.updateCompeted(todoDTO.completed)
        val saved = todoRepository.save(todo)
        return TodoMapper().createTodoDTOWithUserIdFromEntity(saved)
    }


    override fun getTodoListByUserIdWithUnCompleted(id : Long) : List<TodoDTO>{
        return todoRepository.findUndoListByUserId(id).map{TodoMapper().createTodoDTOFromEntity(it)}
    }
    override fun deleteTodo(id: Long){
        todoRepository.deleteById(id)
    }

    override fun deleteTodoWIthStartReward(id: Long) {
        todoRepository.deleteById(id)
    }


}

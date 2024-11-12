package org.alham.alhamfirst.service.orchestrator.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.entity.todo.Todo;
import org.alham.alhamfirst.mapper.TodoMapper;
import org.alham.alhamfirst.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final TodoMapper todoMapper;

    /**
     * 할일 생성
     * startDate는 해당 레이어에서 넣어줌
     *
     * @param todoDTO
     * @return
     */
    @Override
    public Todo createTodo(TodoDTO todoDTO) {
        todoDTO.setStartDate(LocalDate.now());
        Todo todo = todoMapper.createTodoFromDTO(todoDTO);
        User user = User.createTempUser(1L);
        todo.addUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public List<TodoDTO> getTodoList() {
        return todoRepository.findAll().stream().map(todoMapper::createTodoDTOFromEntity).toList();
    }

    @Override
    public List<TodoDTO> listTodo() {
        return todoRepository.findAll().stream().map(todoMapper::createTodoDTOFromEntity).toList();
    }

    @Override
    public TodoDTO getTodoDetail(Long id) {
        return todoRepository.findById(id).map(todoMapper::createTodoDTOFromEntity).orElse(new TodoDTO());
    }

    //TODO - 업데이트 할때마다 stat 변경 로직 적용여부 확인 후 적용
    @Override
    public void updateTodoDetail(Long id, TodoDTO todoDTO) {
        todoRepository.findById(id).ifPresent(todo -> {
            todo.updateTodo(todoDTO.getDetail());
            todoRepository.save(todo);
        });
    }
}

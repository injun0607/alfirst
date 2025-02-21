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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final TodoMapper todoMapper;

    @Override
    public TodoDTO getTodoDetailByUserId(Long id) {
        Todo byUserId = todoRepository.findByUserId(id);
        return todoMapper.createTodoDTOFromEntity(byUserId);
    }

    /**
     * 할일 생성
     * startDate는 해당 레이어에서 넣어줌
     *
     * @param todoDTO
     * @return
     */
    @Override
    @Transactional
    public Todo createTodo(TodoDTO todoDTO) {
        todoDTO.setStartDate(LocalDate.now());
        Todo todo = todoMapper.createTodoFromDTO(todoDTO);
        User user = User.createTempUser(todoDTO.getUserId());
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
    @Transactional
    public TodoDTO updateTodoDetail(TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.getId()).orElseThrow();
        todo.updateTodo(todoDTO.getDetail());
        Todo saved = todoRepository.save(todo);
        return todoMapper.createTodoDTOWithUserIdFromEntity(saved);

    }

    @Override
    public List<TodoDTO> getTodoListByUserIdWithUndo(Long id) {
        List<Todo> undoListByUserId = todoRepository.findUndoListByUserId(id);
        return todoRepository.findUndoListByUserId(id).stream().map(todoMapper::createTodoDTOFromEntity).toList();
    }

    @Override
    @Transactional
    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void deleteTodoWithStatReward(long id) {
        todoRepository.deleteById(id);
    }
}

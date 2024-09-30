package org.alham.alhamfirst.service.todo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.Constant;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.Todo;
import org.alham.alhamfirst.entity.TodoList;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.mapper.TodoMapper;
import org.alham.alhamfirst.repository.todo.TodoListRepository;
import org.alham.alhamfirst.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TodoListServiceImpl implements ToDoListService{

    private final TodoListRepository todoListRepository;

    private final TodoMapper todoMapper;


    @Override
    @Transactional
    public void createTodoList(TodoListDTO todoListDTO) {
        TodoList todoList = todoMapper.createTodoListFromDTO(todoListDTO);
        todoListRepository.save(todoList);
    }

    @Override
    @Transactional
    public void updateTodoList(TodoListDTO todoListDTO) {

        TodoList findTodoList = todoListRepository.findByUserIdWithTodo(todoListDTO.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("TodoList not found with id: " + todoListDTO.getUserId()));

        Map<Long,Todo> todoMap = findTodoList.getTodoList().stream()
                .collect(Collectors.toMap(Todo::getId, Function.identity()));

        todoListDTO.getTodoList().forEach(todoDTO -> {
            //새로 생성된 TODO들은 ID가 0이다.
            if(todoDTO.getId() == Constant.NEW_ENTITY_NUMBER){
                Todo todo = todoMapper.createTodoFromDTO(todoDTO);
                findTodoList.addTodo(todo);
            }else{
                Todo todo = todoMap.get(todoDTO.getId());
                if(todo != null){
                    //수정
                    todoMapper.updateTodoFromDTO(todo, todoDTO);
                    todoMap.remove(todo.getId());
                }
            }
        });

        todoMap.values().forEach(findTodoList::removeTodo);

        todoListRepository.save(findTodoList);
    }



    @Override
    public TodoList getTodoListByToday(long userId) {
        return null;
    }

    @Override
    public Optional<TodoListDTO> getTodoListByDate(long userId, LocalDate date) {
        return todoListRepository.findByUserIdAndDateWithTodoList(userId, date).map(todoList -> {
            TodoListDTO todoListDTOFromEntity = todoMapper.createTodoListDTOFromEntity(todoList);
            todoListDTOFromEntity.setUserId(userId);
            return todoListDTOFromEntity;
        });
    }

    @Override
    public List<TodoListDTO> getAllTodoListByUserId(long userId) {
        return null;
    }
}

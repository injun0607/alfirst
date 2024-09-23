package org.alham.alhamfirst.service.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.entity.Todo;
import org.alham.alhamfirst.entity.TodoList;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.mapper.TodoMapper;
import org.alham.alhamfirst.repository.todo.TodoListRepository;
import org.alham.alhamfirst.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoListServiceImpl implements ToDoListService{

    private final TodoListRepository todoListRepository;



    @Override
    public void createTodoList(long userId) {

        TodoList todoList = new TodoList();
        User user = new User(userId);
        todoList.addUser(user);

        todoListRepository.save(todoList)

    }

    @Override
    public void updateTodoList(long userId, TodoListDTO todoListDTO) {

    }

    @Override
    public TodoList getTodoListByToday(long userId) {
        return null;
    }

    @Override
    public TodoList getTodoListByDate(long userId, String date) {
        return null;
    }
}

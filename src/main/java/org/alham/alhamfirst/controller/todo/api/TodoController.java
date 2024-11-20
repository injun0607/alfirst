package org.alham.alhamfirst.controller.todo.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.service.orchestrator.todo.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todo")
@RestController
public class TodoController {


    private final TodoService todoService;

    @PostMapping("/create")
    public void createTodo(@RequestBody TodoDTO todoDTO){
        todoService.createTodo(todoDTO);
    }

    @GetMapping("/todo-list/todo")
    public List<TodoDTO> listTodo(){
        return todoService.getTodoList();
    }

    @GetMapping("/detail/{id}")
    public TodoDTO getTodoDetail(@PathVariable Long id){
        return todoService.getTodoDetail(id);
    }

    @PostMapping("/update/{id}")
    public void updateTodoDetail(@RequestBody TodoDTO todoDTO){
        todoService.updateTodoDetail(todoDTO);
    }


}

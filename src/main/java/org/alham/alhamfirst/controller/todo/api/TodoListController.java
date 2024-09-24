package org.alham.alhamfirst.controller.todo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.service.todo.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todolist")
@RestController
public class TodoListController {

    private final ToDoListService todoService;


    @GetMapping
    public ResponseEntity<TodoListDTO> getTodoList(){
        LocalDate now = LocalDate.now();
        TodoListDTO todoListByDate = todoService.getTodoListByDate(1L, now);
        return new ResponseEntity<>(todoListByDate, null, 200);

    }




}

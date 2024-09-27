package org.alham.alhamfirst.controller.todo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.AlhamResponse;
import org.alham.alhamfirst.common.enums.ResponseCode;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.service.todo.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        TodoListDTO todoListByDate = todoService.getTodoListByDate(1L, now).orElseGet(TodoListDTO::new);
        return new ResponseEntity<>(todoListByDate, null, 200);

    }

    @PostMapping("/init")
    public ResponseEntity<AlhamResponse> createTodoList(@RequestBody TodoListDTO todoListDTO){

        try {
            //오늘 날짜의 TODOList 가 있으면 업데이트, 없으면 생성
            todoService.getTodoListByDate(todoListDTO.getUserId(), todoListDTO.getDate())
                    .ifPresentOrElse(
                            todoList -> todoService.updateTodoList(todoListDTO),
                            () -> todoService.createTodoList(todoListDTO)
                    );
            return new ResponseEntity<>(new AlhamResponse<>("성공", ResponseCode.SAVE_SUCCESS), null, 200);
        } catch (Exception e){
            log.error("TodoList 생성 실패", e);
            return new ResponseEntity<>(new AlhamResponse("실패",
        }

    }






}

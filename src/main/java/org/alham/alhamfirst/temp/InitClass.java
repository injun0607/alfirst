package org.alham.alhamfirst.temp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.todo.TodoListDTO;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.service.todo.ToDoListService;
import org.alham.alhamfirst.service.user.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitClass {


    private final UserService userService;
    private final ToDoListService toDoListService;



    @PostConstruct
    public void init(){
        log.info("InitClass init");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("alham");
        userDTO.setAge(30);

        User user = userService.createUser(userDTO);


        TodoListDTO todoListDTO = new TodoListDTO();
        todoListDTO.setUserId(user.getId());
        todoListDTO.setDate(LocalDate.now());

        toDoListService.createTodoList(todoListDTO);

        UserDTO userDTO2 = new UserDTO();
        userDTO.setName("aljun");
        userDTO.setAge(29);

        User user2 = userService.createUser(userDTO2);




    }


}

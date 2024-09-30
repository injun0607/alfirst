package org.alham.alhamfirst.dto.todo;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDTO {

    private long id;
    private long userId;
    private LocalDate date;
    private List<TodoDTO> todoList = new ArrayList<>();

    @Builder
    public TodoListDTO(long id,LocalDate date){
        this.id = id;
        this.date = date;
    }

    @Builder
    public TodoListDTO(LocalDate date){
        this.date = date;
    }
}

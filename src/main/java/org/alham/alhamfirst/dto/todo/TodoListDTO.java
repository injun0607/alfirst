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

    public long userId;

    public LocalDate date;

    public List<TodoDTO> todoList = new ArrayList<>();

    @Builder
    public TodoListDTO(LocalDate date){
        this.date = date;
    }
}

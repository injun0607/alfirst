package org.alham.alhamfirst.dto.todo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoListDTO {

    public long userId;

    public String date;

    public List<TodoDTO> todoList;


}

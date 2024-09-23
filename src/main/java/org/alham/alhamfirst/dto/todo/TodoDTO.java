package org.alham.alhamfirst.dto.todo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoDTO {

    private String title;
    private String detail;
    private boolean completed;

}

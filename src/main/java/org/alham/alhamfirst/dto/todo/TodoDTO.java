package org.alham.alhamfirst.dto.todo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoDTO {

    private Long id;
    private String title;
    private String detail;
    private boolean completed;

}

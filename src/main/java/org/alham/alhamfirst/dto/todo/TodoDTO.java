package org.alham.alhamfirst.dto.todo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoDTO {

    private Long id;
    private String detail;
    private boolean completed;
    private LocalDate startDate;

}

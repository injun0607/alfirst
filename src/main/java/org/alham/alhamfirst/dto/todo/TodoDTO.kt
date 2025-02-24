package org.alham.alhamfirst.dto.todo;

import lombok.*;

import java.time.LocalDate;

//@Getter
//@Setter
//@NoArgsConstructor
//@Builder
//@AllArgsConstructor
data class TodoDTO(var id: Long? = null,
                   var userId : Long? = null,
                   var detail : String = "",
                   var completed : Boolean = false,
                   var startDate : LocalDate = LocalDate.now()) {

}

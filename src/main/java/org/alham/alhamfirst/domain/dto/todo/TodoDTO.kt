package org.alham.alhamfirst.domain.dto.todo;

import java.time.LocalDate

data class TodoDTO(var id: Long? = null,
                   var userId : Long? = null,
                   var detail : String = "",
                   var completed : Boolean = false,
                   var startDate : LocalDate = LocalDate.now()) {

}

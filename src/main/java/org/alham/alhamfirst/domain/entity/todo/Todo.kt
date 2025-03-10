package org.alham.alhamfirst.domain.entity.todo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.common.enums.Category;
import org.alham.alhamfirst.domain.entity.User;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "al_todo")
@Getter
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    val id : Long? = null,

    @ManyToOne
    @JoinColumn(name = "al_user_id")
    var user : User? = null,

    var detail : String ="",
    var category: Category? = null,
    var completed : Boolean = false,
    var startDate : LocalDate = LocalDate.now(),
    var endDate : LocalDate? = null,

    @LastModifiedDate
    var editDate : LocalDate = LocalDate.now()
) {

    fun updateCompeted(completed: Boolean){
        if(completed){
            this.endDate = LocalDate.now();
        }
        this.completed = completed;
    }

    fun updateTodo(detail : String){
        this.detail = detail;
    }

    fun addUser(user : User){
        this.user = user;
    }

}

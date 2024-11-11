package org.alham.alhamfirst.entity.todo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.common.enums.Category;
import org.alham.alhamfirst.entity.User;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "al_todo")
@NoArgsConstructor
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "al_user_id")
    private User user;

    private String detail;

    private Category category;

    private boolean completed;
    //시작 날짜
    private LocalDate startDate;

    //달성 날짜
    private LocalDate endDate;

    //수정 날짜
    @LastModifiedDate
    private LocalDate editDate;

    @Builder
    public Todo(String detail,boolean completed, LocalDate startDate){
        this.detail = detail;
        this.completed = completed;
        this.startDate = startDate;
    }

    public void updateCompleted(boolean completed) {
        if(completed){
            this.endDate = LocalDate.now();
        }
        this.completed = completed;
    }


    public void updateTodo(String detail) {
        this.detail = detail;
    }

    public void addUser(User user){
        this.user = user;
    }

}

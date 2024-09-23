package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "al_todo")
@NoArgsConstructor
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;

    private String title;

    private String detail;

    private boolean completed;


    @Builder
    public Todo(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public void updateCompleted(boolean completed) {
        this.completed = completed;
    }


    public void updateTodo(String title, String detail, boolean completed) {
        this.title = title;
        this.detail = detail;
        this.completed = completed;
    }
}

package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "al_todo_list")
@NoArgsConstructor
@Getter
public class TodoList {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "al_user_id")
    private User user;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Todo> todoList = new ArrayList<>();

    private LocalDate date;

    public void addUser(User user){
        this.user = user;
    }

    public void addTodo(Todo todo){
        this.todoList.add(todo);
        todo.addTodoList(this);
    }

    public void removeTodo(Todo todo){
        this.todoList.remove(todo);
    }

    public TodoList(LocalDate date){
        this.date = date;
    }


}

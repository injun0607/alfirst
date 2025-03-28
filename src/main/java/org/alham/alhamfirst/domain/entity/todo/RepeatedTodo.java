package org.alham.alhamfirst.domain.entity.todo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.common.enums.RepeatedStatus;
import org.alham.alhamfirst.domain.entity.User;
import org.alham.alhamfirst.domain.entity.User;

@Entity
@Table(name = "al_repeated_todo")
@NoArgsConstructor
@Getter
public class RepeatedTodo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repeated_todo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "al_user_id")
    private User user;

    private RepeatedStatus repeatedStatus;

    private String title;
    private String detail;


}

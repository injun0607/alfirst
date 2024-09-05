package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "al_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_idx;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

    public User(Long userIdx){
        this.user_idx = userIdx;
    }



}

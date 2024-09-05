package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "al_goal")
@AllArgsConstructor
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goal_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    private String title;
    private String description;
    private Boolean completed;

    public void addTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void updateGoal(String title, String description, Boolean completed){
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    @Builder
    public Goal(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }


}

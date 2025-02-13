package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "al_goal")
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @Column(name = "goal_desc")
    private String description;

    private Boolean completed;

    public void addTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void updateGoal( String description, Boolean completed){
        this.description = description;
        this.completed = completed;
    }

    @Builder
    public Goal(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }


}

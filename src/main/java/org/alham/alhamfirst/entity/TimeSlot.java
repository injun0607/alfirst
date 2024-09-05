package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.common.enums.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "al_time_slot")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "time_slot_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private String startTime;
    private String endTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY)
    private final List<Goal> goalList = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addGoal(Goal goal) {
        goalList.add(goal);
        goal.addTimeSlot(this);
    }

    public void updateTimeSlot(String startTime, String endTime, Category category){
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    public void removeGoal(Goal goal){
        goalList.remove(goal);
    }

    @Builder
    public TimeSlot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}

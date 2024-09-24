package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.alhamfirst.common.enums.WeekStatus;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "al_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private long id;

    //시간표 소유자
    @ManyToOne
    @JoinColumn(name = "al_user_id")
    private User user;

    //시간표 제목
    private String title;

    //시간표 설명
    private String scheduleDesc;

    private LocalDate date;


    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY)
    private List<TimeSlot> timeSlotList = new ArrayList<>();



    public void addUser(User user){
        this.user = user;
    }

    public void addTimeSlot(TimeSlot timeSlot){
        timeSlotList.add(timeSlot);
        timeSlot.addSchedule(this);
    }


    public void updateSchedule(String title, String scheduleDesc){
        this.title = title;
        this.scheduleDesc = scheduleDesc;
    }

    public void removeTimeSlot(TimeSlot timeSlot){
        timeSlotList.remove(timeSlot);
    }

    @Builder
    public Schedule(String title, String scheduleDesc) {
        this.title = title;
        this.scheduleDesc = scheduleDesc;
    }



}

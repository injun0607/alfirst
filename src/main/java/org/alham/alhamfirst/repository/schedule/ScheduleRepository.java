package org.alham.alhamfirst.repository.schedule;

import org.alham.alhamfirst.entity.Schedule;
import org.alham.alhamfirst.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>,ScheduleCustomRepository{



    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN FETCH s.timeSlotList t")
    List<Schedule> findAllScheduleWithTimeSlot();

    /*
     */
    @Query("SELECT DISTINCT t FROM TimeSlot t LEFT JOIN FETCH t.goalList g WHERE t.schedule.id in :scheduleIdList")
    List<TimeSlot> findTimeSlotListByScheduleIdListWithGoal(List<Long> scheduleIdList);

    @Query("SELECT DISTINCT t FROM TimeSlot t LEFT JOIN FETCH t.goalList g WHERE t.schedule.id = :scheduleId")
    List<TimeSlot> findTimeSlotListByScheduleIdWithGoal(@Param("scheduleId") Long scheduleId);

    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN FETCH s.timeSlotList t WHERE s.id = :id")
    Optional<Schedule> findScheduleWithTimeSlotById(Long id);

    @Query("SELECT DISTINCT t FROM TimeSlot t LEFT JOIN FETCH t.goalList g WHERE t.id = :id")
    Optional<TimeSlot> findTimeSlotWithGoalById(Long id);

    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN FETCH s.timeSlotList t WHERE s.id = :id")
    Schedule findScheduleByIdWithTimeSlot(Long id);

}

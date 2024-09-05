package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.dto.schedule.GoalDTO;
import org.alham.alhamfirst.dto.schedule.ScheduleDTO;
import org.alham.alhamfirst.dto.schedule.TimeSlotDTO;
import org.alham.alhamfirst.entity.Goal;
import org.alham.alhamfirst.entity.Schedule;
import org.alham.alhamfirst.entity.TimeSlot;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public Schedule scheduleFromDTO(ScheduleDTO scheduleDTO){
        Schedule schedule = Schedule.builder()
                .title(scheduleDTO.getTitle())
                .scheduleDesc(scheduleDTO.getScheduleDesc()).build();
        if(!scheduleDTO.getTimeSlotList().isEmpty()){
            for(TimeSlotDTO timeSlotDTO : scheduleDTO.getTimeSlotList()){
                schedule.addTimeSlot(timeSlotFromDTO(timeSlotDTO));
            }
        }

        return schedule;
    }


    public TimeSlot timeSlotFromDTO(TimeSlotDTO timeSlotDTO){
        TimeSlot timeSlot = TimeSlot.builder()
                .startTime(timeSlotDTO.getStartTime())
                .endTime(timeSlotDTO.getEndTime()).build();
        if(!timeSlotDTO.getGoalList().isEmpty()){
            for(GoalDTO goalDTO : timeSlotDTO.getGoalList()){
                timeSlot.addGoal(goalFromDTO(goalDTO));
            }
        }

        return timeSlot;
    }

    public Goal goalFromDTO(GoalDTO goalDTO){
        return Goal.builder()
                .title(goalDTO.getTitle())
                .description(goalDTO.getDescription())
                .completed(goalDTO.getCompleted()).build();
    }

    public ScheduleDTO scheduleDTOFromSchedule(Schedule schedule){

        return new ScheduleDTO(schedule);
    }

    public TimeSlotDTO timeSlotDTOFromTimeSlot(TimeSlot timeSlot){
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO(timeSlot);
        if(!timeSlot.getGoalList().isEmpty()){
            for(Goal goal : timeSlot.getGoalList()){
                timeSlotDTO.addGoal(goalDTOFromGoal(goal));
            }
        }

        return timeSlotDTO;
    }

    public GoalDTO goalDTOFromGoal(Goal goal){
        return new GoalDTO(goal);
    }

    public void updateScheduleFromDTO(Schedule schedule, ScheduleDTO scheduleDTO){
        schedule.updateSchedule(scheduleDTO.getTitle(), scheduleDTO.getScheduleDesc());
    }

    public void updateTimeSlotFromDTO(TimeSlot timeSlot, TimeSlotDTO timeSlotDTO){
        timeSlot.updateTimeSlot(timeSlotDTO.getStartTime(), timeSlotDTO.getEndTime(), timeSlotDTO.getCategory());
    }

    public void updateGoalFromDTO(Goal goal, GoalDTO goalDTO){
        goal.updateGoal(goalDTO.getTitle(),goalDTO.getDescription(),goalDTO.getCompleted());
    }



}

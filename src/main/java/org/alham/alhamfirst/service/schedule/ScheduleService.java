package org.alham.alhamfirst.service.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.schedule.GoalDTO;
import org.alham.alhamfirst.dto.schedule.ScheduleDTO;
import org.alham.alhamfirst.dto.schedule.TimeSlotDTO;
import org.alham.alhamfirst.entity.Goal;
import org.alham.alhamfirst.entity.Schedule;
import org.alham.alhamfirst.entity.TimeSlot;
import org.alham.alhamfirst.entity.User;
import org.alham.alhamfirst.mapper.ScheduleMapper;
import org.alham.alhamfirst.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Deprecated
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;


    @Transactional
    public int createSchedule(ScheduleDTO scheduleDTO, long userIdx){
        Schedule schedule = scheduleMapper.scheduleFromDTO(scheduleDTO);
        User user = User.createTempUser(userIdx);
        schedule.addUser(user);

        scheduleRepository.save(schedule);
        return 1;
    }


    @Transactional
    public int updateSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleRepository.findScheduleWithTimeSlotById(scheduleDTO.getId())
                .orElseThrow();
        List<TimeSlot> timeSlotList = scheduleRepository.findTimeSlotListByScheduleIdWithGoal(schedule.getId());

        scheduleMapper.updateScheduleFromDTO(schedule, scheduleDTO);
        Map<Long, TimeSlot> timeSlotMap = timeSlotList.stream()
                .collect(Collectors.toMap(TimeSlot::getId, Function.identity()));

        // 1. 기존 TimeSlot 업데이트 및 삭제 처리
        scheduleDTO.getTimeSlotList().forEach(timeSlotDTO -> {
            TimeSlot timeSlot = timeSlotMap.get(timeSlotDTO.getId());
            if (timeSlot != null) {
                // 기존 TimeSlot 업데이트
                scheduleMapper.updateTimeSlotFromDTO(timeSlot, timeSlotDTO);

                Map<Long, Goal> goalMap = timeSlot.getGoalList().stream()
                        .collect(Collectors.toMap(Goal::getId, Function.identity()));

                // 기존 Goal 업데이트 및 삭제 처리
                timeSlotDTO.getGoalList().forEach(goalDTO -> {
                    Goal goal = goalMap.get(goalDTO.getId());
                    if (goal != null) {
                        scheduleMapper.updateGoalFromDTO(goal, goalDTO);
                        goalMap.remove(goalDTO.getId());
                    } else {
                        // 새로운 Goal 추가
                        Goal newGoal = scheduleMapper.goalFromDTO(goalDTO);
                        timeSlot.addGoal(newGoal);
                    }
                });

                // 삭제된 Goal 처리
                goalMap.values().forEach(timeSlot::removeGoal);
                timeSlotMap.remove(timeSlotDTO.getId());
            } else {
                // 새로운 TimeSlot 추가
                TimeSlot newTimeSlot = scheduleMapper.timeSlotFromDTO(timeSlotDTO);
                schedule.addTimeSlot(newTimeSlot);
            }
        });

        // 삭제된 TimeSlot 처리
        timeSlotMap.values().forEach(schedule::removeTimeSlot);
        // 필요한 경우, 변경된 schedule 저장
        scheduleRepository.save(schedule);

        return 1;
    }




    public List<ScheduleDTO> getAllScheduleListWithGoal(Long userIdx) {
        List<Schedule> scheduleList = scheduleRepository.findAllScheduleWithTimeSlot();
        List<Long> scheduleIdList = scheduleList.stream().map(Schedule::getId).toList();
        List<TimeSlot> timeSlotList = scheduleRepository.findTimeSlotListByScheduleIdListWithGoal(scheduleIdList);

        //타임슬롯의 sheduleId를 확인한다음 , DTO로 변환한다.
        return scheduleList.stream().map(schedule -> {
            List<TimeSlotDTO> timeSlotDTOList = timeSlotList.stream()
                    .filter(timeSlot -> timeSlot.getSchedule().getId() == schedule.getId())
                    .map(timeslot ->{

//                        TimeSlotDTO timeSlotDTO = new TimeSlotDTO(timeslot);
//                        timeslot.getGoalList().forEach(goal -> timeSlotDTO.addGoal(new GoalDTO(goal)));
                        TimeSlotDTO timeSlotDTO = scheduleMapper.timeSlotDTOFromTimeSlot(timeslot);
                        return timeSlotDTO;
                    })
                    .toList();
            ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);
            timeSlotDTOList.forEach(scheduleDTO::addTimeSlot);
            return scheduleDTO;
        }).toList();

    }


    public ScheduleDTO getScheduleWithGoal(Long scheduleIdx) {
        Schedule schedule = scheduleRepository.findById(scheduleIdx).isPresent() ?
                scheduleRepository.findById(scheduleIdx).get() : new Schedule();

        List<TimeSlot> timeSlotList = scheduleRepository.findTimeSlotListByScheduleIdWithGoal(schedule.getId());
        List<TimeSlotDTO> TimeSlotDTOList = timeSlotList.stream().map(scheduleMapper::timeSlotDTOFromTimeSlot).toList();
        ScheduleDTO scheduleDTO = scheduleMapper.scheduleDTOFromSchedule(schedule);
        TimeSlotDTOList.forEach(scheduleDTO::addTimeSlot);

        return scheduleDTO;
    }


}

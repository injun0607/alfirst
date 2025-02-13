package org.alham.alhamfirst.controller.schedule.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.dto.schedule.ScheduleDTO;
import org.alham.alhamfirst.service.schedule.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@RestController
@Deprecated
public class ScheduleApiController {


    private final ScheduleService scheduleService;

    @GetMapping("/create")
    public String createScheduleForm() {
        return "/schedule/schedule-form";
    }


    @PostMapping("/create")
    public String createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        long userIdx = 1L;
        //security로 유저 가져와서 넣어주기
        scheduleService.createSchedule(scheduleDTO,userIdx);

        return "ok";
    }

    @PostMapping("/update")
    public String updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.updateSchedule(scheduleDTO);
        return "ok";
    }

    @GetMapping("/list/{userIdx}")
    public List<ScheduleDTO> scheduleList(@PathVariable Long userIdx) {
        return scheduleService.getAllScheduleListWithGoal(userIdx);
    }

    @GetMapping("{scheduleIdx}")
    public ScheduleDTO getSchedule(@PathVariable Long scheduleIdx) {
        return scheduleService.getScheduleWithGoal(scheduleIdx);
    }




}

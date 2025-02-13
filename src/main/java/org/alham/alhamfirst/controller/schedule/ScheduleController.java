package org.alham.alhamfirst.controller.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.enums.WeekStatus;
import org.alham.alhamfirst.dto.schedule.ScheduleDTO;
import org.alham.alhamfirst.dto.schedule.TimeSlotDTO;
import org.alham.alhamfirst.service.schedule.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Controller
@Deprecated
public class ScheduleController {


    private final ScheduleService scheduleService;


    @GetMapping("/create")
    public String createScheduleForm() {
        return "/schedule/schedule-form";
    }


    @PostMapping("/create")
    public String createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        //security로 유저 가져와서 넣어주기
        scheduleService.createSchedule(scheduleDTO,1);



        return "redirect : /schedule/schedule-form";
    }

    @GetMapping("/list")
    public String scheduleList() {
        return "/schedule/schedule-list";
    }





}

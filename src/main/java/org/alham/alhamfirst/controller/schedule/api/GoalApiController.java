package org.alham.alhamfirst.controller.schedule.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/goal")
@RestController
@Deprecated
public class GoalApiController {

    @PostMapping("/create")
    public void createGoal() {
        log.info("create goal");

    }


}

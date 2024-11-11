package org.alham.alhamfirst.controller.todo.api;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.dto.stat.StatDocument;
import org.alham.alhamfirst.service.orchestrator.stat.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/stat")
public class StatAPIController {

    private final StatService statService;

    @PostMapping("/save/{todoIdx}")
    public ResponseEntity<StatDocument> saveStat(@PathVariable(name = "todoIdx") long todoIdx, @RequestBody Map<String,Integer> statData){
        StatDocument statDocument = statService.saveStat(todoIdx, statData);
        return ResponseEntity.ok(statDocument);
    }

    @GetMapping("/list/{todoIdx}")
    public ResponseEntity<StatDocument> getStat(@PathVariable(name = "todoIdx") long todoIdx){
        StatDocument statDocument = statService.findByTodoIdx(todoIdx);
        return ResponseEntity.ok(statDocument);
    }


}

package org.alham.alhamfirst.controller.todo.api;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.domain.document.stat.StatDocument;
import org.alham.alhamfirst.domain.dto.stat.StatDTO;
import org.alham.alhamfirst.service.orchestrator.ai.AIService;
import org.alham.alhamfirst.service.orchestrator.stat.TodoStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/stat")
public class StatAPIController {

    private final TodoStatService statService;
    private final AIService aiService;

    @PostMapping("/save/{todoIdx}")
    public ResponseEntity<StatDocument> saveStat(@PathVariable(name = "todoIdx") long todoIdx, @RequestBody Map<String,Double> statData){
        StatDocument statDocument = statService.saveStat(todoIdx, statData);
        return ResponseEntity.ok(statDocument);
    }

    @GetMapping("/list/{todoIdx}")
    public ResponseEntity<StatDTO> getStat(@PathVariable(name = "todoIdx") long todoIdx){
        StatDTO statDocument = statService.findByTodoIdx(todoIdx);
        return ResponseEntity.ok(statDocument);
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody Map<String, String> map){
        String description = map.get("description");
        String response = aiService.getAnswer(description);
        return ResponseEntity.ok(response);


    }


}

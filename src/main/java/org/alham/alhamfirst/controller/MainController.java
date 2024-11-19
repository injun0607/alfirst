package org.alham.alhamfirst.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.service.orchestrator.OrchestratorTodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final OrchestratorTodoService orchestratorTodoService;

    @PostMapping("/todo/create")
    public StatDocument create(@RequestBody TodoDTO todoDTO) {
        return orchestratorTodoService.createTodo(todoDTO);
    }

    @GetMapping("/todo/{userId}")
    public List<QuestDTO> getUnDoQuestListByUserId(@PathVariable(name = "userId") Long userId) {
        return orchestratorTodoService.getUnDoQuestListByUserId(userId);
    }


}

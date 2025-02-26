package org.alham.alhamfirst.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.dto.user.UserDTO;
import org.alham.alhamfirst.service.orchestrator.OrchestratorTodoService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
class MainController(private val orchestratorTodoService: OrchestratorTodoService, private val jwtInfo :UserDTO) {

    @PostMapping("/todo/create")
    fun create(@RequestBody todoDTO : TodoDTO) : StatDocument{
        return orchestratorTodoService.createTodo(todoDTO,jwtInfo.id);
    }

    @GetMapping("/undo-list/get")
    fun getUndoQuestListByUserId() : List<QuestDTO> {
        return orchestratorTodoService.getUnDoQuestListByEncryptedUserId(jwtInfo.id);
    }

}

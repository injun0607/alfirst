package org.alham.alhamfirst.controller;

import lombok.extern.slf4j.Slf4j
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.stat.StatDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO
import org.alham.alhamfirst.domain.dto.todo.TodoDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.orchestrator.OrchestratorTodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@Slf4j
class MainController(private val orchestratorTodoService: OrchestratorTodoService) {

    private val log = logger()

    @PostMapping("/todo/create")
    fun questCreate(@AuthenticationPrincipal principal: UserDTO ,@RequestBody todoDTO: TodoDTO): ResponseEntity<QuestDTO>{
        try{
            val quest = orchestratorTodoService.createQuest(todoDTO,principal.id);
            return ResponseEntity(quest, HttpStatus.OK)
        } catch(e: Exception){
            log.error("todo create error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/quest/list")
    fun getUndoQuestListByUserId(@AuthenticationPrincipal principal: UserDTO): ResponseEntity<List<QuestDTO>>{
        try {
            val undoList = orchestratorTodoService.getUnDoQuestListByEncryptedUserId(principal.id)
            return ResponseEntity(undoList, HttpStatus.OK);
        } catch (e: Exception){
            log.error("undo list get error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/todo/{id}/{completed}")
    fun questComplete(@AuthenticationPrincipal principal: UserDTO,
                      @PathVariable id: Long,
                      @PathVariable completed:Boolean): ResponseEntity<UserStatDTO>{
        try{
            return ResponseEntity.ok(orchestratorTodoService.completeQuest(id,principal.id,completed))
        } catch(e: Exception){
            log.error("todo complete change error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/todo/{id}/change")
    fun questChange(@PathVariable id: Long,@RequestBody todoDTO: TodoDTO): ResponseEntity<UserStatDTO>{
        try{
            TODO()
//            return ResponseEntity.ok(orchestratorTodoService.changeQuest(id,principal.id,todoDTO.completed))
        } catch(e: Exception){
            log.error("todo update error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }





}

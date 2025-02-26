package org.alham.alhamfirst.controller;

import lombok.extern.slf4j.Slf4j
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.document.stat.StatDocument
import org.alham.alhamfirst.dto.quest.QuestDTO
import org.alham.alhamfirst.dto.stat.UserStatDTO
import org.alham.alhamfirst.dto.todo.TodoDTO
import org.alham.alhamfirst.dto.user.UserDTO
import org.alham.alhamfirst.service.orchestrator.OrchestratorTodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Slf4j
class MainController(private val orchestratorTodoService: OrchestratorTodoService, private val jwtInfo :UserDTO) {

    private val log = logger()

    @PostMapping("/todo/create")
    fun create(@RequestBody todoDTO: TodoDTO): ResponseEntity<StatDocument>{
        try{
            val todo = orchestratorTodoService.createTodo(todoDTO,jwtInfo.id);
            return ResponseEntity(todo, HttpStatus.OK)
        } catch(e: Exception){
            log.error("todo create error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/undo-list/get")
    fun getUndoQuestListByUserId(): ResponseEntity<List<QuestDTO>>{
        try {
            val undoList = orchestratorTodoService.getUnDoQuestListByEncryptedUserId(jwtInfo.id)
            return ResponseEntity(undoList, HttpStatus.OK);
        } catch (e: Exception){
            log.error("undo list get error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/todo/{id}/completion")
    fun completeTodo(@PathVariable id: Long): ResponseEntity<UserStatDTO>{
        try{
            return ResponseEntity.ok(orchestratorTodoService.completeTodo(id,jwtInfo.id))
        } catch(e: Exception){
            log.error("todo complete error",e)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

}

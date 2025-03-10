package org.alham.alhamfirst.controller.quest

import org.alham.alhamfirst.common.enums.ResponseCode
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.quest.QuestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quest")
class QuestController(
    private val questService: QuestService,
    private val jwtToken: UserDTO
) {

    @PostMapping("/create")
    fun createQuest(@RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        try{
            return ResponseEntity.ok(questService.createQuest(quest,jwtToken.id))
        } catch(e: Exception){
            return ResponseEntity.badRequest().body(QuestDTO())
        }
    }

    @PostMapping("/delete/{questId}")
    fun deleteQuest(questId: String): ResponseCode {
        try{
            questService.deleteQuest(questId,jwtToken.id)
            return ResponseCode.SUCCESS
        } catch (e: Exception){
            return ResponseCode.FAIL
        }
    }

    @PostMapping("/change")
    fun changeQuest(@RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        try{
            return ResponseEntity.ok(questService.changeQuest(quest,jwtToken.id))
        } catch(e: Exception){
            return ResponseEntity.badRequest().body(QuestDTO())
        }
    }

}
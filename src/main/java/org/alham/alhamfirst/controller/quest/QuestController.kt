package org.alham.alhamfirst.controller.quest

import org.alham.alhamfirst.common.enums.ResponseCode
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.quest.QuestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quest")
class QuestController(
    private val questService: QuestService,
    private val jwtToken: UserDTO
) {

    @PostMapping
    fun createQuest(@RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        return ResponseEntity.ok(questService.createQuest(quest,jwtToken.id))
    }
    @GetMapping("/{questId}")
    fun getQuest(@PathVariable questId: String): ResponseEntity<QuestDTO> {
        return ResponseEntity.ok(questService.getQuest(questId,jwtToken.id))
    }
    @GetMapping("/list")
    fun getQuestList(): ResponseEntity<List<QuestDTO>> {
        return ResponseEntity.ok(questService.getQuestList(jwtToken.id))
    }

    @DeleteMapping("/{questId}")
    fun deleteQuest(@PathVariable questId: String): ResponseCode {
        questService.deleteQuest(questId,jwtToken.id)
        return ResponseCode.SUCCESS
    }

    @PostMapping("/{questId}")
    fun changeQuest(@PathVariable questId: String, @RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        quest.id = questId
        return ResponseEntity.ok(questService.changeQuest(quest,jwtToken.id))
    }

    @PostMapping("/{questId}/complete")
    fun completeQuest(@PathVariable questId: String): ResponseCode {
        questService.completeQuest(questId,jwtToken.id)
        return ResponseCode.SUCCESS
    }

    @PostMapping("/{questId}/cancel")
    fun cancelQuest(@PathVariable questId: String): ResponseCode {
        questService.cancelQuest(questId,jwtToken.id)
        return ResponseCode.SUCCESS
    }

}
package org.alham.alhamfirst.controller.quest

import org.alham.alhamfirst.common.enums.ResponseCode
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.quest.QuestService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quest")
class QuestController(
    private val questService: QuestService
) {

    @PostMapping
    fun createQuest(@AuthenticationPrincipal principal: UserDTO,
                    @RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        return ResponseEntity.ok(questService.createQuest(quest,principal.id))
    }
    @GetMapping("/{questId}")
    fun getQuest(@AuthenticationPrincipal principal: UserDTO,
                 @PathVariable questId: String): ResponseEntity<QuestDTO> {
        return ResponseEntity.ok(questService.getQuest(questId,principal.id))
    }
    @GetMapping("/list")
    fun getQuestList(@AuthenticationPrincipal principal: UserDTO): ResponseEntity<List<QuestDTO>> {
        return ResponseEntity.ok(questService.getQuestList(principal.id))
    }

    @DeleteMapping("/{questId}")
    fun deleteQuest(@AuthenticationPrincipal principal: UserDTO,
                    @PathVariable questId: String): ResponseCode {
        questService.deleteQuest(questId,principal.id)
        return ResponseCode.SUCCESS
    }

    @PostMapping("/{questId}")
    fun changeQuest(@AuthenticationPrincipal principal: UserDTO,
                    @PathVariable questId: String,
                    @RequestBody quest: QuestDTO): ResponseEntity<QuestDTO> {
        quest.id = questId
        return ResponseEntity.ok(questService.changeQuest(quest,principal.id))
    }

    @PostMapping("/{questId}/complete")
    fun completeQuest(@AuthenticationPrincipal principal: UserDTO,
                      @PathVariable questId: String): ResponseCode {
        questService.completeQuest(questId,principal.id)
        return ResponseCode.SUCCESS
    }

    @PostMapping("/{questId}/cancel")
    fun cancelQuest(@AuthenticationPrincipal principal: UserDTO,
                    @PathVariable questId: String): ResponseCode {
        questService.cancelQuest(questId,principal.id)
        return ResponseCode.SUCCESS
    }

}
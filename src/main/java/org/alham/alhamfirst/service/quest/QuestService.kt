package org.alham.alhamfirst.service.quest

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.QuestDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.mapper.QuestMapper
import org.alham.alhamfirst.repository.quest.QuestRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.service.stat.UserStatService
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class QuestService(
    private val questRepository: QuestRepository,
    private val aiService: AIService,
    private val userStatService: UserStatService
) {

    val log = logger()

    fun createQuest(quest: QuestDTO,encryptedId: String): QuestDTO {

        val userId = CommonUtil.getDecryptedId(encryptedId)
        quest.userId = userId

        aiService.getAnswer(quest.detail)
            .let { quest.statData = aiService.getStat(it) }

        return questRepository
            .createQuest(QuestMapper().createDocumentFromDTO(quest))
            .let { QuestMapper().createQuestDTOFromDocument(it) }
    }

    fun deleteQuest(questId: String, encryptedId: String){

        val userId = CommonUtil.getDecryptedId(encryptedId)
        questRepository.deleteQuest(questId, userId)
            .let {
                log.info("[RESULT]Quest deleted completed. questId : $questId , userId : $userId")
            }

    }

    fun changeQuest(quest: QuestDTO, encryptedId: String): QuestDTO{

        //aiService.get
        val userId = CommonUtil.getDecryptedId(encryptedId)
        quest.userId = userId

        if(!quest.completed){
            throw AlhamCustomException(HttpStatus.NOT_FOUND,"Quest is already completed")
        }

        aiService.getAnswer(quest.detail)
            .let { quest.statData = aiService.getStat(it) }

        questRepository.updateQuest(QuestMapper().createDocumentFromDTO(quest))
            ?.let {
                log.info("[RESULT] updateQuest $quest")
                return QuestMapper().createQuestDTOFromDocument(it)
            }?: throw AlhamCustomException(HttpStatus.NOT_FOUND,"Quest not found")

    }


    fun getQuest(questId: String, encryptedId: String): QuestDTO{

        val userId = CommonUtil.getDecryptedId(encryptedId)
        return questRepository.getQuest(questId, userId)
            ?.let{ QuestMapper().createQuestDTOFromDocument(it) }
            ?: throw AlhamCustomException(HttpStatus.NOT_FOUND,"Quest not found")
    }

    fun getQuestList(encryptedId: String): List<QuestDTO>{

        val userId = CommonUtil.getDecryptedId(encryptedId)
        return questRepository.getQuestListByUserIdWithUnCompleted(userId)
            .map { QuestMapper().createQuestDTOFromDocument(it) }

    }

    fun completeQuest(questId: String, encryptedId: String){
        val userId = CommonUtil.getDecryptedId(encryptedId)
        questRepository.getQuest(questId, userId)
            ?.let {
                it.completed = true
                questRepository.updateQuest(it)
                userStatService.updateUserStat(userId,it.statData,true)
            } ?: AlhamCustomException(HttpStatus.NOT_FOUND,"Quest not found")
    }

    fun cancelQuest(questId: String, encryptedId: String){

        val userId = CommonUtil.getDecryptedId(encryptedId)
        questRepository.getQuest(questId, userId)
            ?.let {
                it.completed = false
                questRepository.updateQuest(it)
                userStatService.updateUserStat(userId,it.statData,false)
            } ?: throw AlhamCustomException(HttpStatus.NOT_FOUND,"Quest not found")

    }

}
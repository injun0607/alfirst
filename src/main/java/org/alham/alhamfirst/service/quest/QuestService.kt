package org.alham.alhamfirst.service.quest

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.QuestDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.mapper.QuestMapper
import org.alham.alhamfirst.repository.quest.QuestRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service

@Service
class QuestService(
    private val questRepository: QuestRepository,
    private val aiService: AIService
) {

    val log = logger()

    fun createQuest(quest: QuestDTO,encryptedId: String): QuestDTO {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            quest.userId = userId

            aiService.getAnswer(quest.detail)
                .let { quest.statData = aiService.getStat(it) }

            return questRepository
                .createQuest(QuestMapper().createDocumentFromDTO(quest))
                .let { QuestMapper().createQuestDTOFromDocument(it) }
        } catch(exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in creating quest",exception)
        }
    }

    fun deleteQuest(questId: String, encryptedId: String){
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
             questRepository.deleteQuest(questId, userId)
                 .let { log.info("Quest deleted. delete quest_id = {}, user_id={}", it?.id,it?.userId) }
        } catch(exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in deleting quest", exception)
        }
    }

    fun changeQuest(quest: QuestDTO, encryptedId: String): QuestDTO{
        //aiService.get
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            quest.userId = userId

            aiService.getAnswer(quest.detail)
                .let { quest.statData = aiService.getStat(it) }

            questRepository.updateQuest(QuestMapper().createDocumentFromDTO(quest))
                .let {
                    log.info("Quest changed. change quest_id = {}, user_id={}", it.id,it.userId)
                    return QuestMapper().createQuestDTOFromDocument(it)
                }
        }catch (exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in changing quest", exception)
        }


    }

}
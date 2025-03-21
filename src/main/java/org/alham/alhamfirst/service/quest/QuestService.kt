package org.alham.alhamfirst.service.quest

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.QuestDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.mapper.QuestMapper
import org.alham.alhamfirst.repository.quest.QuestRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.service.stat.UserStatService
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service

@Service
class QuestService(
    private val questRepository: QuestRepository,
    private val aiService: AIService,
    private val userStatService: UserStatService
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

            if(!quest.completed){
                throw Exception("Quest is already completed")
            }

            aiService.getAnswer(quest.detail)
                .let { quest.statData = aiService.getStat(it) }

            questRepository.updateQuest(QuestMapper().createDocumentFromDTO(quest))
                ?.let {
                    log.info("Quest changed. change quest_id = {}, user_id={}", it.id,it.userId)
                    return QuestMapper().createQuestDTOFromDocument(it)
                }?: throw Exception("Quest not found")
        }catch (exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in changing quest", exception)
        }
    }


    fun getQuest(questId: String, encryptedId: String): QuestDTO{
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)

            return questRepository.getQuest(questId, userId)
                ?.let{ QuestMapper().createQuestDTOFromDocument(it) }
                ?: throw Exception("Quest not found")
        } catch(exception: Exception){

            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in getting quest", exception)
        }

    }

    fun getQuestList(encryptedId: String): List<QuestDTO>{
        try {
            val userId = CommonUtil.getDecryptedId(encryptedId)
            return questRepository.getQuestListByUserIdWithUnCompleted(userId)
                .map { QuestMapper().createQuestDTOFromDocument(it) }
        } catch(exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in getting quest list", exception)
        }
    }

    //TODO - 유저 스탯연동필요
    fun completeQuest(questId: String, encryptedId: String){
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            questRepository.getQuest(questId, userId)
                ?.let {
                    it.completed = true
                    questRepository.updateQuest(it)
                    userStatService.updateUserStat(userId,it.statData,true)
                } ?: throw Exception("Quest not found")

        } catch(exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in completing quest", exception)
        }
    }

    fun cancelQuest(questId: String, encryptedId: String){
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            questRepository.getQuest(questId, userId)
                ?.let {
                    it.completed = false
                    questRepository.updateQuest(it)
                    userStatService.updateUserStat(userId,it.statData,false)
                } ?: throw Exception("Quest not found")

        } catch(exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in canceling quest", exception)
        }
    }

}
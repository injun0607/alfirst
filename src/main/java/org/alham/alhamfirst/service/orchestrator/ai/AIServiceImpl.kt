package org.alham.alhamfirst.service.orchestrator.ai;

import com.fasterxml.jackson.databind.ObjectMapper
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.config.AiConfig
import org.springframework.ai.chat.client.ChatClient
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux


@Service


class AIServiceImpl(
    private val chatClient: ChatClient
):AIService{

    val log = logger()
    override fun getAnswer(question: String): String{
        log.info("[ACTION] getAnswer");
        log.info("[DATA] question : ${question}")

        return chat(question)
            .collectList()
            .map{chunks -> chunks.joinToString("")}
            .block() ?: throw AlhamCustomException(HttpStatus.INTERNAL_SERVER_ERROR ,"Answer is not exist");
    }

    override fun chat(question: String): Flux<String> {
        log.info("[EXTERNAL] chat");
        try{
            return chatClient.prompt()
                .system{it.text(AiConfig.DEFAULT_SYSTEM_CHAT)}
                .user(question)
                .stream()
                .content();
        }catch (e: Exception){
            throw AlhamCustomException(HttpStatus.INTERNAL_SERVER_ERROR ,"AI Chat Error", e.cause);
        }
    }

    override fun getStat(statJson: String):MutableMap<String, Double> {
        log.info("[ACTION] getStat");

        val objectMapper = ObjectMapper()

        try {
            log.info("[DATA] statJson : $statJson");
            val result = objectMapper.readValue(statJson, Map::class.java);
            val category = result["Category"] as String
            val statList = result["StatList"] as MutableMap<String, Double>;
            return statList
        } catch(e: Exception) {
            log.error("[ERROR] ${e.message}");
            throw AlhamCustomException(HttpStatus.INTERNAL_SERVER_ERROR ,"Stat Json Parsing Error", e.cause);
            //TODO()
        }

    }

}


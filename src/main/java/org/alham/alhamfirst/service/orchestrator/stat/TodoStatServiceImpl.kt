package org.alham.alhamfirst.service.orchestrator.stat;


import org.alham.alhamfirst.common.error.MongoCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.stat.StatDocument
import org.alham.alhamfirst.domain.dto.stat.StatDTO
import org.alham.alhamfirst.mapper.StatMapper
import org.alham.alhamfirst.repository.stat.TodoStatRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.service.orchestrator.stat.preprocess.PreProcessService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class TodoStatServiceImpl(
        private val preProcessService: PreProcessService,
        private val statRepository: TodoStatRepository,
        private val webClient: WebClient,
        private val aiService: AIService
) :TodoStatService {

    val log = logger()

    override fun saveStat(todoIdx: Long, statData: MutableMap<String,Double>): StatDocument {
        log.info("StatService saveStat")
        return statRepository.save(StatDocument(todoIdx = todoIdx, statData = statData))
    }

    override fun calculateStat(todoDetail: String): StatDTO {
        log.info("StatService calculateStat")

        try{
            val preProcessed = preProcess(todoDetail)
            /*
             * desc를 받고 -> stat을 계산해야한다.
             * 1.받은 것을 한번 전처리
             * 2. 전처리한 것을 ai쪽에 보내고
             * 3. ai에서 받은 결과를 가지고 stat을 계산
             */
            return callAi(preProcessed)
        }catch(e: Exception){
            log.error("StatService calculateStat error")
            throw MongoCustomException("StatService calculateStat error")
        }
    }

    override fun updateStat(stat: String){
        log.info("StatService updateStat");
        //stat을 받아서 db에 업데이트
    }

    override fun findByTodoIdx(todoIdx: Long): StatDTO {
        val statDocument = statRepository.findByTodoIdx(todoIdx)
        return StatMapper().createStatDTOFromDocument(statDocument)
    }

    override fun findListInTodoIdxList(todoIdxList: List<Long>): List<StatDTO>{
        val byTodoIdxIn = statRepository.findByTodoIdxIn(todoIdxList)
        return byTodoIdxIn.map(StatMapper()::createStatDTOFromDocument)
    }


    private fun preProcess(todoDetail: String): String{
        return todoDetail
    }

    private fun callAi(preProcessed: String): StatDTO {
        val answer = aiService.getAnswer(preProcessed)
        val statData = aiService.getStat(answer)
        return StatDTO(statData = statData)
    }

}

package org.alham.alhamfirst.service.orchestrator.stat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.MongoCustomError;
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.mapper.StatMapper;
import org.alham.alhamfirst.repository.stat.TodoStatRepository;
import org.alham.alhamfirst.repository.stat.UserStatRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService;
import org.alham.alhamfirst.service.orchestrator.stat.preprocess.PreProcessService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
class TodoStatServiceImpl(
        private val preProcessService: PreProcessService,
        private val statMapper: StatMapper,
        private val statRepository: TodoStatRepository,
        private val webClient: WebClient,
        private val aiService: AIService
) :TodoStatService {

    val log = logger()

    override fun saveStat(todoIdx: Long, statData: MutableMap<String,Double>): StatDocument{
        log.info("StatService saveStat")
        return statRepository.save(StatDocument(todoIdx = todoIdx, statData = statData))
    }


    @Override
    public StatDTO calculateStat(String todoDetail) {
        log.info("StatService calculateStat");

        try{
            /*
             * desc를 받고 -> stat을 계산해야한다.
             * 1.받은 것을 한번 전처리
             * 2. 전처리한 것을 ai쪽에 보내고
             * 3. ai에서 받은 결과를 가지고 stat을 계산
             */
            String preProcessed = proProcess(todoDetail);

            return callAi(preProcessed);
        } catch (Exception e){
            log.error("StatService calculateStat error");
            throw new MongoCustomError("StatService calculateStat error");
        }
    }

    @Override
    public void updateStat(String stat) {
        log.info("StatService updateStat");
        //stat을 받아서 db에 업데이트
    }

    @Override
    public StatDTO findByTodoIdx(long todoIdx) {
        StatDocument statDocument = statRepository.findByTodoIdx(todoIdx);
        return statMapper.createStatDTOFromDocument(statDocument);
    }

    @Override
    public List<StatDTO> findListInTodoIdxList(List<Long> todoIdxList) {
        List<StatDocument> byTodoIdxIn = statRepository.findByTodoIdxIn(todoIdxList);

        return byTodoIdxIn.stream().map(statMapper::createStatDTOFromDocument).toList();
    }

    private String proProcess(String todoDetail){
        return todoDetail;
    }


    private StatDTO callAi(String preProcessed){

        String answer = aiService.getAnswer(preProcessed);
        Map<String, Double> statData = aiService.getStat(answer);

        StatDTO statDTO = new StatDTO();
        statDTO.setStatData(statData);

        return statDTO;
    }






}

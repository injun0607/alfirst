package org.alham.alhamfirst.service.orchestrator.stat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.repository.stat.StatRepository;
import org.alham.alhamfirst.service.orchestrator.stat.preprocess.PreProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService{

    private final PreProcessService preProcessService;
    private final StatRepository statRepository;
    private final WebClient webClient;

    @Override
    public StatDocument saveStat(long todoIdx,  Map<String, Integer> statData) {
        StatDocument statDocument = new StatDocument(todoIdx, statData);
        log.info("StatService saveStat");
        return statRepository.save(statDocument);
    }

    @Override
    public String calculateStat(String todoDetail) {
        log.info("StatService calculateStat");
        /*
         * desc를 받고 -> stat을 계산해야한다.
         * 1.받은 것을 한번 전처리
         * 2. 전처리한 것을 ai쪽에 보내고
         * 3. ai에서 받은 결과를 가지고 stat을 계산
         */
        String preProcessed = proProcess(todoDetail);

        StatDTO statDTO = callAi(preProcessed);

        return null;

    }

    @Override
    public void updateStat(String stat) {
        log.info("StatService updateStat");
        //stat을 받아서 db에 업데이트
    }

    @Override
    public StatDocument findByTodoIdx(long todoIdx) {
        return statRepository.findByTodoIdx(todoIdx);
    }

    private String proProcess(String todoDetail){
        return todoDetail;
    }


    private StatDTO callAi(String preProcessed){

        Mono<String> stringMono = webClient.post()
                .uri("/api/calculate")
                .bodyValue(preProcessed)
                .retrieve()
                .bodyToMono(String.class);

        return null;
    }






}

package org.alham.alhamfirst.service.stat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.service.stat.preprocess.PreProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService{

    private final PreProcessService preProcessService;
    private final WebClient webClient;

    @Override
    public String calculateStat(String desc) {

        log.info("StatService calculateStat");
        /*
         * desc를 받고 -> stat을 계산해야한다.
         * 1.받은 것을 한번 전처리
         * 2. 전처리한 것을 ai쪽에 보내고
         * 3. ai에서 받은 결과를 가지고 stat을 계산
         */
        String preProcessed = proProcess(desc);

        String stat = callAi(preProcessed);

        return stat;

    }

    @Override
    public void updateStat(String stat) {
        log.info("StatService updateStat");
        //stat을 받아서 db에 업데이트
    }

    private String proProcess(String desc){
        return null;
    }


    private String callAi(String preProcessed){

        Mono<String> stringMono = webClient.post()
                .uri("/api/calculate")
                .bodyValue(preProcessed)
                .retrieve()
                .bodyToMono(String.class);
        return null;
    }






}

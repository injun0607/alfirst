package org.alham.alhamfirst.service.orchestrator.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.config.AiConfig;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIServiceImpl implements AIService{

    private final ChatClient chatClient;

    //TODO - 이부분 병렬처리 가능한것같음 . 청크를 병렬로 돌려서 처리하는것도 생각해봐야겠음
    @Override
    public String getAnswer(String question) {
        log.info("question : {}",question);
         return chat(question)
                .collectList()  // 모든 청크를 리스트로 수집
                .map(chunks -> String.join("", chunks))  // 청크들을 하나의 문자열로 결합
                .block();
    }


    @Override
    public Flux<String> chat(String question) {
        return chatClient.prompt()
                .system(s -> s.text(AiConfig.DEFAULT_SYSTEM_CHAT))
                .user(question)
                .stream()
                .content();
    }

    @Override
    public Map<String, Double> getStat(String statJson) {
        log.info("getStat statJson : {}",statJson);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> result = new HashMap<>();
        Map<String, Double> statList = new HashMap<>();
        try{
            log.info("statJson : {}",statJson);
            result = objectMapper.readValue(statJson, Map.class);
            String category = (String) result.get("Category");
            statList = (Map<String, Double>) result.get("StatList");



        }catch (Exception e){
            //TODO - 스탯변환 실패했을때 오류 잡아야함
            log.error(e.getMessage());
        }
        return statList;
    }
}

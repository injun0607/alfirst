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

    @Override
    public String getAnswer(String question) {
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

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Double> result = new HashMap<>();
        try{
            result = objectMapper.readValue(statJson, Map.class);
        }catch (Exception e){
            //TODO - 스탯변환 실패했을때 오류 잡아야함
            log.error("AIService getStat error");
        }
        return result;
    }
}

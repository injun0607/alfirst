package org.alham.alhamfirst.service.orchestrator.ai;


import reactor.core.publisher.Flux;

import java.util.Map;

public interface AIService {

    public String getAnswer(String question);

    public Flux<String> chat(String question);

    public Map<String ,Double> getStat(String statJson);

}

package org.alham.alhamfirst.service.orchestrator.ai;

import reactor.core.publisher.Flux;

interface AIService {

    fun getAnswer(question: String): String

    fun chat(question: String): Flux<String>

    fun getStat(statJson: String): MutableMap<String ,Double>

}

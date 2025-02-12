package org.alham.alhamfirst.service.orchestrator.ai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.client.ChatClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AIServiceImplTest {
    private AIServiceImpl aiService;

    @Mock
    private ChatClient chatClient; // 🔥 ChatClient를 Mock으로 선언

    @Mock
    private ChatClient.ChatClientRequestSpec prompt; // 내부 메서드 체이닝을 위한 Mock

    @Mock
    private ChatClient.StreamResponseSpec stream; // stream() 반환 객체 Mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aiService = new AIServiceImpl(chatClient);
    }

    @Test
    void testGetAnswerWithMockedFlux() {
        // 🔥 1. Mock 객체 동작 설정
        when(chatClient.prompt()).thenReturn(prompt);
        when(prompt.system(anyString())).thenReturn(prompt);
        when(prompt.user(anyString())).thenReturn(prompt);
        when(prompt.stream()).thenReturn(stream);
        when(stream.content()).thenReturn(Flux.just("Hello", " ", "World!")); // Flux 데이터 설정

        // 🎯 2. 테스트 실행
        String result = aiService.getAnswer("How are you?");

        // ✅ 3. 결과 검증
        assertEquals("Hello World!", result);

        // 👀 4. 메서드 호출 검증
        verify(chatClient).prompt();
        verify(prompt).system(anyString());
        verify(prompt).user("How are you?");
        verify(prompt).stream();
    }

    @Test
    void testGetAnswerWithEmptyFlux() {
        // 빈 Flux 반환 설정
        when(chatClient.prompt()).thenReturn(prompt);
        when(prompt.system(anyString())).thenReturn(prompt);
        when(prompt.user(anyString())).thenReturn(prompt);
        when(prompt.stream()).thenReturn(stream);
        when(stream.content()).thenReturn(Flux.empty());

        String result = aiService.getAnswer("What's up?");
        assertEquals("", result); // 빈 문자열이어야 함
    }

    @Test
    void testGetAnswerWithErrorFlux() {
        // 에러 발생하는 Flux 설정
        when(chatClient.prompt()).thenReturn(prompt);
        when(prompt.system(anyString())).thenReturn(prompt);
        when(prompt.user(anyString())).thenReturn(prompt);
        when(prompt.stream()).thenReturn(stream);
        when(stream.content()).thenReturn(Flux.error(new RuntimeException("Mock Error!")));

        // StepVerifier를 사용한 에러 검증
        StepVerifier.create(Flux.defer(() -> Flux.just(aiService.getAnswer("Error Test"))))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void testFlux(){

        Flux<String> testString = Flux.just("Hello", " ", "World!");
        assertEquals(testString.blockFirst(), "Hello");




    }

}


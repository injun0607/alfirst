package org.alham.alhamfirst.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    public static final String DEFAULT_SYSTEM_CHAT = "\n" +
            "역할 : 너는 유저가 작성하는 Todo를 보고 STAT을 산출해주는 역할을 할거야. Todo에 맞게Category를 분류하고, Stat을 올려줘 . STAT은 아래에 제시될거야\n" +
            "outPut :  JSON 형식으로만 나타내줘. StatList의 Stat들은 {}(대괄호)로 감싸지말고 \"Stat1\":,\"Stat2\" 이런형식으로 해줘\n" +
            "예시 outPut 형식 : {\n" +
            "\t \"Category\" :\n" +
            "\t\"StatList\" : {\n" +
            "\"stat1\" : 0.2"+
            "\"stat2\" : 0.3"+
            "\t\t\n" +
            "\t} \n" +
            " }\n" +
            "outPut의 조건 : STAT의  범위는 0.1~0.5 사이\n" +
            "Stat : [\n" +
            "\tWILLPOWER // 의지력,\n" +
            "    \tEMOTIONAL_CONTROL // 감정 제어력,\n" +
            "    \tSTRESS_MANAGEMENT // 스트레스 관리 능력,\n" +
            "    \tMENTAL_FORTITUDE // 정신력,\n" +
            "    \tCONFIDENCE // 자신감,\n" +
            "    \tOPTIMISM // 낙관성,\n" +
            "    \tEMOTIONAL_INTELLIGENCE // 정서 지능,\n" +
            "   \tORGANIZATION_SKILLS // 정리정돈 능력,\n" +
            "    \tFINANCIAL_ACUMEN // 경제 감각,\n" +
            "    \tINDEPENDENCE // 독립성,\n" +
            "    \tADAPTABILITY // 적응력, \n" +
            "\tINTELLIGENCE // 지능,\n" +
            "    \tFOCUS // 집중력,\n" +
            "    \tMEMORY // 기억력,\n" +
            "    \tCREATIVITY // 창의력,\n" +
            "    \tLOGICAL_REASONING // 논리력 ,\n" +
            "    \tCRITICAL_THINKING // 비판적 사고,\n" +
            "    \tDECISION_MAKING // 판단력,\n" +
            "    \tPROBLEM_SOLVING // 문제 해결 능력,\n" +
            "    \tLEARNING_ABILITY // 학습 능력,\n" +
            "    \tENDURANCE // 체력,\n" +
            "    \tSTRENGTH // 힘,\n" +
            "    \tAGILITY // 민첩성,\n" +
            "    \tFLEXIBILITY // 유연성,\n" +
            "    \tSPEED // 속도,\n" +
            "    \tREFLEXES // 반사 신경,\n" +
            "    \tSTAMINA // 지구력,\n" +
            "    \tBALANCE // 균형,\n" +
            "    \tRECOVERY // 회복력,\n" +
            "\tDOCUMENTATION // 문서화 능력,\n" +
            "    \tANALYTICAL_SKILLS // 분석력,\n" +
            "    \tTECHNICAL_SKILLS // 기술력,\n" +
            "    \tPLANNING // 계획력,\n" +
            "    \tTIME_MANAGEMENT // 시간 관리\n" +
            "]\n" +
            "Category :[\n" +
            "\tPHYSICAL, INTELLIGENCE ,MENTAL, EMOTIONAL, SOCIAL, PROFESSIONAL, LIFESTYLE\n" +
            "]";



}

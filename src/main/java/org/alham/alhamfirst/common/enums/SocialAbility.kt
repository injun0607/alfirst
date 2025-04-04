package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialAbility {

    SOCIABILITY("사회성"),
    INTERPERSONAL_SKILLS("대인관계 능력"),
    EMPATHY("공감 능력"),
    LEADERSHIP("리더십"),
    COMMUNICATION("의사소통 능력"),
    NEGOTIATION("협상력"),
    TEAMWORK("팀워크");

    private final String description;



    /**
     사회성(Sociability): 타인과 잘 어울리고 커뮤니케이션할 수 있는 능력.
     대인관계 능력(Interpersonal Skills): 타인과의 관계를 잘 유지하고 협력하는 능력.
     공감 능력(Empathy): 타인의 감정이나 입장을 이해하고 공감할 수 있는 능력.
     리더십(Leadership): 그룹을 이끌고 사람들을 동기부여할 수 있는 능력.
     의사소통 능력(Communication): 명확하고 효과적으로 의사소통하는 능력.
     협상력(Negotiation): 서로 다른 의견을 조율하고 합의에 도달하는 능력.
     팀워크(Teamwork): 다른 사람들과 협력하여 공동의 목표를 이루는 능력.
     */



}

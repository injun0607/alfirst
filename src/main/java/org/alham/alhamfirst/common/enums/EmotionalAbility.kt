package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmotionalAbility {

    WILLPOWER("의지력"),
    EMOTIONAL_CONTROL("감정 제어력"),
    STRESS_MANAGEMENT("스트레스 관리 능력"),
    MENTAL_FORTITUDE("정신력"),
    CONFIDENCE("자신감"),
    OPTIMISM("낙관성"),
    EMOTIONAL_INTELLIGENCE("정서 지능");

    private final String description;



    /**
     의지력(Willpower): 어려운 상황에서도 꾸준히 목표를 향해 나아가는 정신적 힘.
     감정 제어력(Emotional Control): 감정을 잘 통제하고 평온을 유지하는 능력.
     스트레스 관리 능력(Stress Management): 스트레스를 잘 다루고 이를 극복하는 능력.
     정신력(Mental Fortitude): 어려운 상황에서도 정신적인 힘을 유지하는 능력.
     자신감(Confidence): 자신에 대한 신뢰, 새로운 도전이나 어려움에 대한 자신감.
     낙관성(Optimism): 긍정적인 마인드셋을 유지하는 능력.
     정서 지능(Emotional Intelligence): 자신의 감정을 이해하고, 타인의 감정을 잘 파악하는 능력.
     */



}

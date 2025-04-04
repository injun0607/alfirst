package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfessionalAbility {

    DOCUMENTATION("문서화 능력"),
    ANALYTICAL_SKILLS("분석력"),
    TECHNICAL_SKILLS("기술력"),
    PLANNING("계획력"),
    TIME_MANAGEMENT("시간 관리");

    private final String description;



    /**
     문서화 능력(Documentation): 정보를 정리하고 문서로 표현하는 능력.
     분석력(Analytical Skills): 복잡한 데이터를 분석하고 통찰을 도출하는 능력.
     기술력(Technical Skills): 기술적 능력, 예를 들어 코딩, 디자인, 데이터 처리 등의 특정 분야 능력.
     계획력(Planning): 목표를 달성하기 위한 계획을 세우고 이를 실행하는 능력.
     시간 관리(Time Management): 시간을 효율적으로 사용하고 계획대로 일을 처리하는 능력.
     */



}

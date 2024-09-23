package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MentalAbility {

    INTELLIGENCE("지능"),
    FOCUS("집중력"),
    MEMORY("기억력"),
    CREATIVITY("창의력"),
    LOGICAL_REASONING("논리력"),
    CRITICAL_THINKING("비판적 사고"),
    DECISION_MAKING("판단력"),
    PROBLEM_SOLVING("문제 해결 능력"),
    LEARNING_ABILITY("학습 능력");

    private final String description;



    /**
     지능(Intelligence): 문제를 해결하고 새로운 정보를 학습하는 능력.
     집중력(Focus): 주어진 과제나 목표에 오랜 시간 집중할 수 있는 능력.
     기억력(Memory): 정보를 얼마나 잘 기억하고 활용하는지.
     창의력(Creativity): 새로운 아이디어를 내고 문제를 창의적으로 해결하는 능력.
     논리력(Logical Reasoning): 논리적으로 사고하고 문제를 해결하는 능력.
     비판적 사고(Critical Thinking): 정보를 분석하고 평가하여 결론을 도출하는 능력.
     판단력(Decision-Making): 빠르고 정확한 결정을 내릴 수 있는 능력.
     문제 해결 능력(Problem Solving): 복잡한 문제를 분석하고 해결하는 능력.
     학습 능력(Learning Ability): 새로운 지식을 빠르게 습득하는 능력.
     */



}

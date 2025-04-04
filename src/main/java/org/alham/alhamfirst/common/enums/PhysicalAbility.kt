package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum PhysicalAbility {


    ENDURANCE("체력"),
    STRENGTH("힘"),
    AGILITY("민첩성"),
    FLEXIBILITY("유연성"),
    SPEED("속도"),
    REFLEXES("반사 신경"),
    STAMINA("지구력"),
    BALANCE("균형"),
    RECOVERY("회복력");

    private final String description;



    /**
     * 체력(Endurance): 신체 활동을 지속할 수 있는 능력, 지구력.
     * 힘(Strength): 근력을 나타내는 스탯, 무거운 물체를 들거나 강한 힘을 사용하는 능력.
     * 민첩성(Agility): 신속하게 움직이거나 회피하는 능력.
     * 유연성(Flexibility): 신체가 얼마나 유연하게 움직일 수 있는지.
     * 속도(Speed): 달리기나 움직임의 속도를 나타내는 능력.
     * 반사 신경(Reflexes): 빠르게 반응하는 능력, 신속한 의사결정이나 물리적 반응에 도움을 줌.
     * 지구력(Stamina): 장시간 동안 지속적으로 신체적 활동을 할 수 있는 능력.
     * 균형(Balance): 신체의 균형을 잘 유지하는 능력.
     * 회복력(Recovery): 피로하거나 상처를 입었을 때, 얼마나 빠르게 회복하는지.
     */



}

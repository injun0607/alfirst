package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

enum class DayStatus(val dayName: String, val dayValue: Int) {
    NONE("None",0),
    MON("Monday",1),
    TUE("Tuesday",2),
    WED("Wednesday",3),
    THU("Thursday",4),
    FRI("Friday",5),
    SAT("Saturday",6),
    SUN("Sunday",7);
}

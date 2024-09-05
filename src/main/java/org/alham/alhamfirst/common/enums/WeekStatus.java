package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum WeekStatus {

    M("Monday"),T("Tuesday"),W("Wednesday"),Th("Thursday"),F("Friday"),Sa("Saturday"),Su("Sunday");

    private final String weekStatus;

}

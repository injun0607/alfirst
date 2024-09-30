package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(1),
    GET_SUCCESS(100),
    SAVE_SUCCESS(101),
    UPDATE_SUCCESS(102),
    FAIL(-1),
    GET_FAIL(-100),
    SAVE_FAIL(-101),
    UPDATE_FAIL(-102);

    private final int code;


}

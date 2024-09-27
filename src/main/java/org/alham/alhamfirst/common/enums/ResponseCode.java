package org.alham.alhamfirst.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(1),
    SAVE_SUCCESS(100),
    FAIL(0),
    SAVE_FAIL(-100);

    private final int code;


}

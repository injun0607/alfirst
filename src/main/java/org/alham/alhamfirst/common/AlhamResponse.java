package org.alham.alhamfirst.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.alham.alhamfirst.common.enums.ResponseCode;

@Getter
@Setter
@RequiredArgsConstructor
public class AlhamResponse<T> {

    private final String msg;

    private final ResponseCode code;

    private T data;

    public AlhamResponse(String msg, ResponseCode code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }


}

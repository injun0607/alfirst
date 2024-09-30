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

    /*
    GET, SAVE, UPDATE 성공, 실패 응답 생성
     */
    public static <T> AlhamResponse<T> createGetSuccess (String msg, T data) {
        return new AlhamResponse<>(msg, ResponseCode.GET_SUCCESS, data);
    }

    public static <T> AlhamResponse<T> createGetSuccess (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.GET_SUCCESS);
    }

    public static <T> AlhamResponse<T> createGetFail (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.GET_FAIL);
    }


    public static <T> AlhamResponse<T> createSaveSuccess (String msg, T data) {
        return new AlhamResponse<>(msg, ResponseCode.SAVE_SUCCESS, data);

    }

    public static <T> AlhamResponse<T> createSaveSuccess (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.SAVE_SUCCESS);
    }

    public static <T> AlhamResponse<T> createSaveFail (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.SAVE_FAIL);
    }

    public static <T> AlhamResponse<T> createUpdateSuccess (String msg, T data) {
        return new AlhamResponse<>(msg, ResponseCode.UPDATE_SUCCESS, data);
    }

    public static <T> AlhamResponse<T> createUpdateSuccess (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.UPDATE_SUCCESS);
    }

    public static <T> AlhamResponse<T> createUpdateFail (String msg) {
        return new AlhamResponse<>(msg, ResponseCode.UPDATE_FAIL);
    }

}

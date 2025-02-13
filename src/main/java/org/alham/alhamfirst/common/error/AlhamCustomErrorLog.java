package org.alham.alhamfirst.common.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//TODO - 이걸로 로그 구현해줘야함
public class AlhamCustomErrorLog {

    //발생한 클래스
    private String className;

    //발생한 메소드
    private String methodName;

    //에러메시지
    private String errorMessage;

    //에러 파람값
    private String param;

    //누가 호출했는지
    private String sessionId;


}

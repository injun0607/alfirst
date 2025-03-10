package org.alham.alhamfirst.common.exception;

//TODO - 이걸로 로그 구현해줘야함
class AlhamCustomErrorLog(
    val className: String = "",
    val methodName: String = "",
    val errorMessage: String = "",
    val param: String = "",
    val sessionId: String = "",
    val exception: Exception
) {

}

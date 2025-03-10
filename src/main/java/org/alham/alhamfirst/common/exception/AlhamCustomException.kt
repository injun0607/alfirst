package org.alham.alhamfirst.common.exception;

class AlhamCustomException(
    message: String,
    cause: Throwable? = null
): RuntimeException(message,cause){}

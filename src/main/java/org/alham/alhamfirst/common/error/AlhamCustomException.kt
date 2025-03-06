package org.alham.alhamfirst.common.error;

class AlhamCustomException(
    message: String,
    cause: Throwable? = null
): RuntimeException(message,cause){}

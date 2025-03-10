package org.alham.alhamfirst.common.exception;

class MariaDBCustomException(
    message: String,
    cause:Throwable?= null
) : RuntimeException(message,cause){}

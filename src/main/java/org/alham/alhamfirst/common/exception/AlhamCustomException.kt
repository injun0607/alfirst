package org.alham.alhamfirst.common.exception;

import org.springframework.http.HttpStatus

class AlhamCustomException(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String,
    override val cause: Throwable? = null
): RuntimeException(message,cause){}

package org.alham.alhamfirst.common.exception

import org.alham.alhamfirst.common.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val log = logger()

    @ExceptionHandler(AlhamCustomException::class)
    fun handleCustomException(ex: AlhamCustomException): ResponseEntity<ErrorResponse> {
        log.error("CustomException occurred: ${ex.message}")
        return ResponseEntity.status(ex.status)
            .body(ErrorResponse(message = ex.message ?: ""))
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error("Unexpected error: ${ex.message}", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(code = "500", message = "Internal Server Error"))
    }

}

class ErrorResponse(
    val code: String ="",
    val message: String =""
)
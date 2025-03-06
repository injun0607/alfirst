package org.alham.alhamfirst.common.error;

public class AlhamCustomException extends RuntimeException{
    public AlhamCustomException(String message) {
        super(message);
    }

    public AlhamCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

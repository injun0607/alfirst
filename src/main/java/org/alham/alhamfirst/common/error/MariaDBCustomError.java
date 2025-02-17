package org.alham.alhamfirst.common.error;

public class MariaDBCustomError extends RuntimeException{
    public MariaDBCustomError(String message) {
        super(message);
    }

    public MariaDBCustomError(String message, Throwable cause) {
        super(message, cause);
    }

}

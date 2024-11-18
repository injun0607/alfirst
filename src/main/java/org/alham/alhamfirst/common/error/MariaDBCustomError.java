package org.alham.alhamfirst.common.error;

public class MariaDBCustomError extends RuntimeException{
    public MariaDBCustomError(String message) {
        super(message);
    }

}

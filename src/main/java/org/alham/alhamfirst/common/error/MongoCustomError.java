package org.alham.alhamfirst.common.error;

public class MongoCustomError extends RuntimeException{
    public MongoCustomError(String message) {
        super(message);
    }
}

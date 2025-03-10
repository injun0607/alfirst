package org.alham.alhamfirst.common.exception;

class MongoCustomException(message: String, cause: Throwable?=null): RuntimeException(message,cause){}

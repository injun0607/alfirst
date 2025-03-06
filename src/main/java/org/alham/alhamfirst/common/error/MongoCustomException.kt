package org.alham.alhamfirst.common.error;

class MongoCustomException(message: String, cause: Throwable?=null): RuntimeException(message,cause){}

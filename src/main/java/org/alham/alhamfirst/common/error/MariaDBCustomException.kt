package org.alham.alhamfirst.common.error;

class MariaDBCustomException(
    message: String,
    cause:Throwable?= null
) : RuntimeException(message,cause){}

package org.alham.alhamfirst.service

import com.mongodb.MongoException
import com.mongodb.MongoQueryException
import org.bson.BsonDocument
import org.junit.jupiter.api.Test

class CommonErrorTest {

    @Test
    fun `common_error_test`(){

        //궁금한 부분 상위 에러로 받으면 하위에러 확인 가능한가?
        val response = BsonDocument()
        val e = MongoQueryException(response, null)
        try{
            throw e
        }catch (e: MongoException) {
            println("MongoException")
            println(e.javaClass)
        }
    }


}
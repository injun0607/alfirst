package org.alham.alhamfirst.repository.stat

import org.alham.alhamfirst.common.error.MongoCustomError
import org.alham.alhamfirst.document.stat.StatDocument
import org.alham.alhamfirst.document.stat.UserStatDocument
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class UserStatRepositoryImpl(
    private val mongoTemplate: MongoTemplate
): UserStatRepository {


    override fun createUserStatDocument(userId: Long): UserStatDocument {
        return mongoTemplate.insert(UserStatDocument(userId = userId))
    }

    override fun findByUserId(userId: Long): UserStatDocument {
        val query = Query(Criteria.where("userId").`is`(userId))
        return mongoTemplate.findOne(query, UserStatDocument::class.java) ?: throw MongoCustomError("UserStatDocument not found")
    }

    override fun updateUserStat(userId: Long, statData: Map<String,Double>): UserStatDocument{
        val query = Query(Criteria.where("userId").`is`(userId))
        val update = Update()

        statData.map{(key,value)->
            update.inc("userStatData.$key",value)
        }

        return mongoTemplate.findAndModify(
            query, update, FindAndModifyOptions.options().returnNew(true), UserStatDocument::class.java
        )?: throw MongoCustomError("UserStatDocument not found")

    }


}
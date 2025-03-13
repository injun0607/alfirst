package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.domain.document.mission.UserMissionDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class UserMissionRepository (private val mongoTemplate: MongoTemplate){

    fun getUserMissionListByUserId(userId: Long): UserMissionDocument? {
        val query = Query(Criteria.where("userId").`is`(userId))

        return mongoTemplate.findOne(query, UserMissionDocument::class.java)
    }


}
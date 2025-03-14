package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.domain.document.mission.UserMissionDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class UserMissionRepository (private val mongoTemplate: MongoTemplate){

    fun getUserMissionListByUserIdAndRegDate(userId: Long,regDate: LocalDate): UserMissionDocument? {
        val query = Query(Criteria.where("userId").`is`(userId).and("regDate").`is`(regDate))

        return mongoTemplate.findOne(query, UserMissionDocument::class.java)
    }

    fun registerUserMission(repeatedStatus: RepeatedStatus){

    }


}
package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.domain.document.mission.UserMissionDocument
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.UpdateDefinition
import org.springframework.data.mongodb.core.update
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class UserMissionRepository (private val mongoTemplate: MongoTemplate){
    fun getUserMission(userMissionId: String ,userId: Long): UserMissionDocument?{

        val query = Query(Criteria
            .where("_id").`is`(userMissionId)
            .and("userId").`is`(userId)
        )

        return mongoTemplate.findOne(query, UserMissionDocument::class.java)
    }

    fun createUserMission(userMissionDocument: UserMissionDocument): UserMissionDocument{
        return mongoTemplate.insert(userMissionDocument)
    }

    fun getUserMissionByRepeatedStatus(userId: Long, regDate: LocalDate, repeatedStatus: RepeatedStatus): UserMissionDocument? {
        val query = Query(Criteria.where("userId")
            .`is`(userId).and("regDate").`is`(regDate)
            .and("repeatedStatus").`is`(repeatedStatus))

        return mongoTemplate.findOne(query, UserMissionDocument::class.java)
    }

    fun updateUserMissionList(userMissionDocument: UserMissionDocument): UserMissionDocument? {
        val query = Query(Criteria
            .where("_id").`is`(userMissionDocument.id)
            .and("userId").`is`(userMissionDocument.userId)
        )

        val update = Update()
            .set("userMissionList", userMissionDocument.userMissionList)

        return mongoTemplate.findAndModify(query, update, FindAndModifyOptions().returnNew(true), UserMissionDocument::class.java)
    }

    fun completeUserMission(userId: Long, userMissionId: String, missionId: String, complete: Boolean): UserMissionDocument?{
        val query = Query(
            Criteria.where("_id").`is`(userMissionId)
                .and("userId").`is`(userId)
                .and("userMissionList.missionId").`is`(missionId)
        )

        val update = Update()
            .set("userMissionList.$.missionComplete", complete)
            .set("userMissionList.$.completeDateTime", LocalDate.now())

        return mongoTemplate.findAndModify(query, update, FindAndModifyOptions().returnNew(true), UserMissionDocument::class.java)

    }

}
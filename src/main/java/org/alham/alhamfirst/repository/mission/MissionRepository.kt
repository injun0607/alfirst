package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.domain.document.mission.MissionDocument
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.FindAndReplaceOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class MissionRepository(private val mongoTemplate: MongoTemplate) {
    fun createMission(mission: MissionDocument): MissionDocument?{
        return mongoTemplate.insert(mission)
    }

    fun getMission(id: String, userId: Long): MissionDocument? {
        val query = Query(Criteria
            .where("_id").`is`(id)
            .and("userId").`is`(userId)
        )
        return mongoTemplate.findOne(query, MissionDocument::class.java)
    }

    fun getMissionList(userId: Long): List<MissionDocument>{
        val query = Query(Criteria
            .where("userId").`is`(userId).and("useFlag").`is`(true)
        )
        return mongoTemplate.find(query, MissionDocument::class.java)
    }

    fun getMissionListCnt(userId: Long): Long{
        val query = Query(Criteria
            .where("userId").`is`(userId).and("useFlag").`is`(true)
        )
        return mongoTemplate.count(query, MissionDocument::class.java)
    }


    fun updateMission(mission: MissionDocument): MissionDocument? {
        val query = Query(Criteria
            .where("_id").`is`(mission.id)
            .and("userId").`is`(mission.userId)
        )
        return mongoTemplate.findAndReplace(query, mission, FindAndReplaceOptions().returnNew())
    }

    fun deleteMission(id: String, userId: Long): MissionDocument? {
        val query = Query(Criteria
            .where("_id").`is`(id)
            .and("userId").`is`(userId)
        )
        return mongoTemplate.findAndRemove(query, MissionDocument::class.java)
    }

    fun updateStreakAndLastEndDate(mission: MissionDocument): MissionDocument? {
        val query = Query(Criteria
            .where("_id").`is`(mission.id)
            .and("userId").`is`(mission.userId)
        )

        val update = Update()
            .set("streak", mission.streak)
            .set("maxStreak", mission.maxStreak)
            .set("lastEndDate", mission.lastEndDate)

        return mongoTemplate.findAndModify(query,update, FindAndModifyOptions().returnNew(true),MissionDocument::class.java)
    }

}
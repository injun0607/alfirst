package org.alham.alhamfirst.repository.quest

import com.mongodb.client.result.DeleteResult
import org.alham.alhamfirst.domain.document.QuestDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAndRemove
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class QuestRepository(private val mongoTemplate: MongoTemplate){

    fun createQuest(quest: QuestDocument):QuestDocument {
        return mongoTemplate.insert(quest)
    }

    fun deleteQuest(id: String,userId: Long): QuestDocument? {
        val query = Query(Criteria
            .where("_id").`is`(id)
            .and("userId").`is`(userId)
        )

        return mongoTemplate.findAndRemove(query, QuestDocument::class.java)
    }
    fun updateQuest(quest: QuestDocument): QuestDocument {
        return mongoTemplate.save(quest)
    }

}

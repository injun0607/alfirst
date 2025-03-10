package org.alham.alhamfirst.repository.quest

import org.alham.alhamfirst.common.Constant
import org.alham.alhamfirst.domain.document.QuestDocument
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class QuestRepositoryTest{

    @Mock
    private lateinit var mongoTemplate: MongoTemplate

    @InjectMocks
    private lateinit var questRepository: QuestRepository

    @Test
    fun `test createQuest`(){

        val quest = QuestDocument(
            id = "asd",
            userId = 1,
            detail = "test",
            completed = false,
            statData = mutableMapOf(),
            startDate = LocalDateTime.now(),
            endDate = Constant.INIT_DATE_TIME
        )

        Mockito.`when`(mongoTemplate.insert(quest)).thenReturn(quest)

        val result = questRepository.createQuest(quest)
        assertEquals(quest,result)
    }

    @Test
    fun `test deleteQuest`(){
        val quest = QuestDocument(
            id = "asd",
            userId = 1,
            detail = "test",
            completed = false,
            statData = mutableMapOf(),
            startDate = LocalDateTime.now(),
            endDate = Constant.INIT_DATE_TIME
        )

        val query = Query(Criteria.where("_id").`is`("asd"))

        Mockito.`when`(mongoTemplate.findAndRemove(query,QuestDocument::class.java))
            .thenReturn(quest)

        val result = questRepository.deleteQuest("asd")
        assertEquals(quest,result)
    }

    @Test
    fun `test updateQuest`(){
        //업데이트 되면 변경이 되어야지?

        val quest = QuestDocument(
            id = "asd",
            userId = 1,
            detail = "test",
            completed = false,
            statData = mutableMapOf(),
            startDate = LocalDateTime.now(),
            endDate = Constant.INIT_DATE_TIME
        )

        val changeQuest = QuestDocument(
            id = "asd",
            userId = 1,
            detail = "change",
            completed = false,
            statData = mutableMapOf(),
            startDate = LocalDateTime.now(),
            endDate = Constant.INIT_DATE_TIME
        )

        Mockito.`when`(mongoTemplate.save(changeQuest)).thenReturn(changeQuest)

        val result = questRepository.updateQuest(changeQuest)
        assertEquals(result , changeQuest)

    }

}
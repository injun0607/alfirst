package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MissionServiceTest(){

    private val missionRepository: MissionRepository = mock()
    private val missionMapper : MissionMapper = MissionMapper()


    @Test
    fun `test basic`(){
        var one = 1;
        assertEquals(1, one)
    }

    @Test
    fun `test register`(){
        //given

        //when
        //then
    }




}



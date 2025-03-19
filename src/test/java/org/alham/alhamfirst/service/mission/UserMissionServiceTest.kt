package org.alham.alhamfirst.service.mission

import io.mockk.*
import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.domain.document.mission.MissionDocument
import org.alham.alhamfirst.domain.document.mission.MissionInfo
import org.alham.alhamfirst.domain.document.mission.UserMissionInfo
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.repository.mission.UserMissionRepository
import org.alham.alhamfirst.service.orchestrator.ai.AIService
import org.alham.alhamfirst.util.CommonUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class UserMissionServiceTest{


    private val missionRepository: MissionRepository = mockk()
    private val userMissionRepository: UserMissionRepository = mockk(relaxed = true)
    private val aiService: AIService = mockk(relaxed = true)
    private val service = spyk(UserMissionService(userMissionRepository,missionRepository, aiService))

    private val userId = 1L
    private val encryptedId = "encryptedUserId"

    @BeforeEach
    fun setUp() {
        mockkObject(CommonUtil)
        every { CommonUtil.getDecryptedId(encryptedId) } returns userId
        every { CommonUtil.getMondayOfWeek(any()) } returns LocalDate.now()
        every { CommonUtil.getFirstDayOfMonth(any()) } returns LocalDate.now()
        every { CommonUtil.getFirstWeekendDayOfWeek(any()) } returns LocalDate.now()
    }

    @Test
    fun `DAILY - 기존 UserMission 존재, 신규 Mission 추가되는 케이스`() {
        // Given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.DAILY

        // 기존 UserMission에는 missionId 1,2만 있음
        val existingUserMission = UserMissionDTO(
            userId = userId,
            repeatedStatus = repeatedStatus,
            userMissionList = mutableListOf(
                UserMissionInfo(missionId = "1", missionDetail = "기존 미션 1"),
                UserMissionInfo(missionId = "2", missionDetail = "기존 미션 2")
            ),
            regDate = today
        )

        // 새로 추가된 missionId 3
        val missionList = listOf(
            MissionDocument("1", userId = userId, "기존 미션1", MissionInfo(RepeatedStatus.DAILY)),
            MissionDocument("2", userId = userId, "기존 미션2", MissionInfo(RepeatedStatus.DAILY)),
            MissionDocument("3", userId = userId, "신규 미션1", MissionInfo(RepeatedStatus.DAILY)) // 신규 등장
        )

        every { missionRepository.getMissionList(userId) } returns missionList

        every {
            userMissionRepository.getUserMissionByRepeatedStatus(userId, any(), repeatedStatus)
        } returns
                MissionMapper().createUserMissionEntityFromDTO(existingUserMission)

        // AI 서비스 Mock (stat 계산)
        every { aiService.getAnswer(any()) } returns "AI Answer"

        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))

        every { aiService.getStat(any()) } returns statMap

        // When
        val result = service.getTodayUserMissionList(encryptedId)

        // Then
        val dailyMission = result.dailyMission.userMissionList
        assertEquals(3, dailyMission.size, "기존 2개 + 신규 1개로 총 3개 되어야 함")

        // 신규 미션 3이 추가되었는지 확인
        assertTrue(dailyMission.any { it.missionId == "3" })

        // Repository 저장이 발생했는지 (존재하면 저장 안함 -> validMissionList에서 리스트 추가로 끝남)
        verify(exactly = 0) { userMissionRepository.createUserMission(any()) }
//        verify(exactly = 4) { userMissionRepository.updateUserMissionList(any()) }
    }

    @Test
    fun `데일리미션 체크 기존 Mission 존재 하지 않을때 - 미션추가`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.DAILY
        //when
        every{
            userMissionRepository.getUserMissionByRepeatedStatus(userId, today, repeatedStatus)
        } returns null

        val missionList = listOf(
            MissionDocument("1", userId = userId, "기존 미션1", MissionInfo(RepeatedStatus.DAILY)),
            MissionDocument("2", userId = userId, "기존 미션2", MissionInfo(RepeatedStatus.DAILY)),
            MissionDocument("3", userId = userId, "기존 미션3", MissionInfo(RepeatedStatus.DAILY)),
            MissionDocument("4", userId = userId, "기존 미션4", MissionInfo(RepeatedStatus.DAILY)),
        )

        every{
            missionRepository.getMissionList(userId)
        } returns missionList

        // AI 서비스 Mock (stat 계산)
        every { aiService.getAnswer(any()) } returns "AI Answer"

        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))

        every { aiService.getStat(any()) } returns statMap

        val result = service.getTodayUserMissionList(encryptedId)
        //then
        assertEquals(4, result.dailyMission.userMissionList.size)
        verify(exactly = 1) { userMissionRepository.createUserMission(any()) }
    }

    @Test
    fun `WEEKLY MISSION 체크 - 기존 WEEKLIY 미션 존재하지않음`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.WEEKLY

        val mondayOfWeek = CommonUtil.getMondayOfWeek(today)
        //when
        every{
            userMissionRepository.getUserMissionByRepeatedStatus(userId, mondayOfWeek, repeatedStatus)
        } returns null

        val missionList = listOf(
            MissionDocument("1", userId = userId, "주간 미션1", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("2", userId = userId, "주간 미션2", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("3", userId = userId, "주간 미션3", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("4", userId = userId, "주간 미션4", MissionInfo(RepeatedStatus.WEEKLY)),
        )

        every{
            missionRepository.getMissionList(userId)
        } returns missionList

        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))
        every { aiService.getStat(any()) } returns statMap

        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then
        assertEquals(4,todayUserMissionList.weeklyMission.userMissionList.size)
        assertEquals(mondayOfWeek,todayUserMissionList.weeklyMission.regDate)


        todayUserMissionList.weeklyMission.userMissionList.forEach {
            println(it.missionDetail)
        }

        verify(exactly = 1) { userMissionRepository.createUserMission(any()) }

    }

    @Test
    fun `WeeklyMission - 기존 미션에 추가시 작업`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.WEEKLY
        val mondayOfWeek = CommonUtil.getMondayOfWeek(today)

        //when
        val existingUserMission = UserMissionDTO(
            userId = userId,
            repeatedStatus = repeatedStatus,
            userMissionList = mutableListOf(
                UserMissionInfo(missionId = "1", missionDetail = "기존 미션 1"),
                UserMissionInfo(missionId = "2", missionDetail = "기존 미션 2")
            ),
            regDate = today
        )

        val missionList = listOf(
            MissionDocument("1", userId = userId, "기존 미션1", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("2", userId = userId, "기존 미션2", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("3", userId = userId, "신규 미션1", MissionInfo(RepeatedStatus.WEEKLY)) // 신규 등장
        )

        every { missionRepository.getMissionList(userId) } returns missionList
        every { userMissionRepository.getUserMissionByRepeatedStatus(userId,mondayOfWeek,repeatedStatus) }
            .returns(MissionMapper().createUserMissionEntityFromDTO(existingUserMission))

        // AI 서비스 Mock (stat 계산)
        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))
        every { aiService.getStat(any()) } returns statMap

        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then

        //미션 반환시 미션리스트 3개
        assertEquals(3, todayUserMissionList.weeklyMission.userMissionList.size)

        //valid
        verify(exactly = 1) { userMissionRepository.updateUserMissionList(any()) }

    }

    @Test
    fun `MONTHLY 신규 생성`(){
        //given
        val today = LocalDate.now()
        var repeatedStatus = RepeatedStatus.MONTHLY
        var firstDayOfMonth = CommonUtil.getFirstDayOfMonth(today)

        //when
        every {
            userMissionRepository.getUserMissionByRepeatedStatus(userId, firstDayOfMonth, repeatedStatus)
        } returns null

        val missionList = listOf(
            MissionDocument("1", userId = userId, "월간 미션1", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("2", userId = userId, "월간 미션2", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("3", userId = userId, "월간 미션3", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("4", userId = userId, "월간 미션4", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("5", userId = userId, "주간 미션1", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("6", userId = userId, "주간 미션2", MissionInfo(RepeatedStatus.WEEKLY)),
        )

        every{
            missionRepository.getMissionList(userId)
        } returns missionList

        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))
        every { aiService.getStat(any()) } returns statMap

        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then

        assertEquals(4,todayUserMissionList.monthlyMission.userMissionList.size)
        assertEquals(CommonUtil.getFirstDayOfMonth(today),todayUserMissionList.monthlyMission.regDate)


        verify (exactly = 1) { userMissionRepository.createUserMission(any()) }

    }

    @Test
    fun `MONTHLY 기존 미션에 추가`() {
        //given
        val today = LocalDate.now()
        var repeatedStatus = RepeatedStatus.MONTHLY
        var firstDayOfMonth = CommonUtil.getFirstDayOfMonth(today)

        //when
        val existingUserMission = UserMissionDTO(
            userId = userId,
            repeatedStatus = repeatedStatus,
            userMissionList = mutableListOf(
                UserMissionInfo(missionId = "1", missionDetail = "기존 미션 1"),
                UserMissionInfo(missionId = "2", missionDetail = "기존 미션 2")
            ),
            regDate = today
        )

        val missionList = listOf(
            MissionDocument("1", userId = userId, "월간 미션1", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("2", userId = userId, "월간 미션2", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("3", userId = userId, "월간 미션3", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("4", userId = userId, "월간 미션4", MissionInfo(RepeatedStatus.MONTHLY)),
            MissionDocument("5", userId = userId, "주간 미션1", MissionInfo(RepeatedStatus.WEEKLY)),
            MissionDocument("6", userId = userId, "주간 미션2", MissionInfo(RepeatedStatus.WEEKLY)),
        )

        every { missionRepository.getMissionList(userId) } returns missionList
        every { userMissionRepository.getUserMissionByRepeatedStatus(userId, firstDayOfMonth, repeatedStatus) }
            .returns(MissionMapper().createUserMissionEntityFromDTO(existingUserMission))

        // AI 서비스 Mock (stat 계산)
        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER", 0.3), Pair("STAMINA", 0.7))
        every { aiService.getStat(any()) } returns statMap

        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then

        assertEquals(4, todayUserMissionList.monthlyMission.userMissionList.size)
        assertEquals(CommonUtil.getFirstDayOfMonth(today), todayUserMissionList.monthlyMission.regDate)

        verify(exactly = 2) { userMissionRepository.updateUserMissionList(any()) }
    }


    @Test
    fun `WEEKDAY - 신규 미션추가`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.WEEKDAY
        val mondayOfWeek = CommonUtil.getMondayOfWeek(today)
        //when
        every{
            userMissionRepository.getUserMissionByRepeatedStatus(userId, today, repeatedStatus)
        } returns null

        every{
            userMissionRepository.getUserMissionByRepeatedStatus(userId, today, RepeatedStatus.DAILY)
        } returns null

        every {
            userMissionRepository.getUserMissionByRepeatedStatus(userId, mondayOfWeek, RepeatedStatus.WEEKEND)
        }

        val missionList = listOf(
            MissionDocument("1", userId = userId, "주중 미션1", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("2", userId = userId, "주중 미션2", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("3", userId = userId, "주중 미션3", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("4", userId = userId, "주중 미션4", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("5", userId = userId, "주말 미션1", MissionInfo(RepeatedStatus.WEEKEND)),
            MissionDocument("6", userId = userId, "주말 미션2", MissionInfo(RepeatedStatus.WEEKEND)),
            MissionDocument("7", userId = userId, "일일 미션1", MissionInfo(RepeatedStatus.DAILY)),
        )

        every{
            missionRepository.getMissionList(userId)
        } returns missionList

        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER",0.3),Pair("STAMINA",0.7))
        every { aiService.getStat(any()) } returns statMap

        val result = service.getTodayUserMissionList(encryptedId)
        //then

        val weekdayMission = result.weekDayMission.userMissionList

        assertEquals(4,weekdayMission.size)

        verify (exactly = 2) { userMissionRepository.createUserMission(any()) }

    }

    @Test
    fun `WEEKDAY - 기존미션에 신규 미션 추가`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.WEEKDAY
        //when
        val userMission = UserMissionDTO(
            userId = userId,
            repeatedStatus = repeatedStatus,
            userMissionList = mutableListOf(
                UserMissionInfo(missionId = "1", missionDetail = "기존 미션 1"),
                UserMissionInfo(missionId = "2", missionDetail = "기존 미션 2")
            ),
            regDate = today
        )

        val missionList = listOf(
            MissionDocument("1", userId = userId, "주중 미션1", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("2", userId = userId, "주중 미션2", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("3", userId = userId, "주중 미션3", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("4", userId = userId, "주중 미션4", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("5", userId = userId, "주말 미션1", MissionInfo(RepeatedStatus.WEEKEND)),
            MissionDocument("6", userId = userId, "주말 미션2", MissionInfo(RepeatedStatus.WEEKEND)),
        )

        every { missionRepository.getMissionList(userId) } returns missionList
        every { userMissionRepository.getUserMissionByRepeatedStatus(userId, today, repeatedStatus) }
            .returns(MissionMapper().createUserMissionEntityFromDTO(userMission))

        every { aiService.getAnswer(any()) } returns "AI Answer"
        val statMap = mapOf(Pair("WILLPOWER", 0.3), Pair("STAMINA", 0.7))
        every { aiService.getStat(any()) } returns statMap


        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then
        todayUserMissionList.weekDayMission.userMissionList.forEach {
            println(it.missionDetail)
        }

        assertEquals(4,todayUserMissionList.weekDayMission.userMissionList.size)

    }

    @Test
    fun `WEEKEND - 신규 등록 안되는것 확인`(){
        //given
        val today = LocalDate.now()
        val repeatedStatus = RepeatedStatus.WEEKEND
        val firstDayOfWeekend = CommonUtil.getFirstWeekendDayOfWeek(today)
        //when
        every {
            userMissionRepository.getUserMissionByRepeatedStatus(userId, firstDayOfWeekend, repeatedStatus)
        } returns null

        val missionList = listOf(
            MissionDocument("1", userId = userId, "주중 미션1", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("2", userId = userId, "주중 미션2", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("3", userId = userId, "주중 미션3", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("4", userId = userId, "주중 미션4", MissionInfo(RepeatedStatus.WEEKDAY)),
            MissionDocument("5", userId = userId, "주말 미션1", MissionInfo(RepeatedStatus.WEEKEND)),
            MissionDocument("6", userId = userId, "주말 미션2", MissionInfo(RepeatedStatus.WEEKEND)),
        )

        every {
            missionRepository.getMissionList(userId)
        } returns missionList

        val todayUserMissionList = service.getTodayUserMissionList(encryptedId)
        //then
        assertEquals(0, todayUserMissionList.weekEndMission.userMissionList.size)

    }

}
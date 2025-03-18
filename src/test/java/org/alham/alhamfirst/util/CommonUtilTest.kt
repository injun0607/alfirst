package org.alham.alhamfirst.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate


class CommonUtilTest{

    @Test
    fun `test_mondayOfWeek`(){

        val date = LocalDate.of(2025, 3, 16)
        val mondayOfWeek = CommonUtil.getMondayOfWeek(date)

        val date2 = LocalDate.of(2025,3,23)
        val mondayOfWeek2 = CommonUtil.getMondayOfWeek(date2)

        println(mondayOfWeek)

        assertEquals(LocalDate.of(2025, 3, 10), mondayOfWeek)
        assertEquals(LocalDate.of(2025, 3, 17), mondayOfWeek2)

    }

    @Test
    fun `test_monthOfFirsyDay`(){
        val date = LocalDate.of(2025, 3, 18)
        val firstDayOfMonth = CommonUtil.getFirstDayOfMonth(date)

        val date2 = LocalDate.of(2025,3,3)
        val firstDayOfMonth2 = CommonUtil.getFirstDayOfMonth(date2)

        println(firstDayOfMonth)

        assertEquals(LocalDate.of(2025, 3, 1), firstDayOfMonth)
        assertEquals(LocalDate.of(2025, 3, 1), firstDayOfMonth2)
    }

    @Test
    fun `test_weekendOfFirstDay`(){
        val date = LocalDate.of(2025, 3, 22)
        val firstWeekendDayOfWeek = CommonUtil.getFirstWeekendDayOfWeek(date)

        val date2 = LocalDate.of(2025,3,16)
        val secondWeekendDayOfWeek = CommonUtil.getFirstWeekendDayOfWeek(date2)


        assertEquals(LocalDate.of(2025, 3, 22), firstWeekendDayOfWeek)
        assertEquals(LocalDate.of(2025, 3, 15), secondWeekendDayOfWeek)
    }


}
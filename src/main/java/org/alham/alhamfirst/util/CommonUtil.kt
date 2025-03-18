package org.alham.alhamfirst.util;

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class CommonUtil {

    companion object {
        fun getDecryptedId(encryptedId: String): Long {
            return AESUtil.decrypt(encryptedId).toLong()
        }

        fun getEncryptedId(id: Long): String {
            return AESUtil.encrypt(id.toString())
        }

        fun getMondayOfWeek(date: LocalDate): LocalDate {
            return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        }

        fun getFirstDayOfMonth(date: LocalDate): LocalDate {
            return date.with(TemporalAdjusters.firstDayOfMonth())
        }

        fun getFirstWeekendDayOfWeek(date: LocalDate): LocalDate {
            when(date.dayOfWeek){
                DayOfWeek.SUNDAY -> return date.minusDays(1)
                else -> return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
            }
        }

    }


}

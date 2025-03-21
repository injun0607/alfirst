package org.alham.alhamfirst.common.enums

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTypeTest{


    @Test
    fun test(){
        val userType = UserType.BASIC
        println("ROLE_"+userType)
        assertEquals("ROLE_BASIC", "ROLE_"+UserType.BASIC)
    }
}
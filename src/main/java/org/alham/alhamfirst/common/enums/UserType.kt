package org.alham.alhamfirst.common.enums;



enum class UserType(
        val maxMissionCnt: Int,
        val maxUpdateCnt: Int
) {
    BASIC(10,15),
    PREMIUM(20,30),
    ADMIN(100,100);
}

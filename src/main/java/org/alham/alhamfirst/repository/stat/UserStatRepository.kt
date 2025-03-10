package org.alham.alhamfirst.repository.stat;

import org.alham.alhamfirst.domain.document.stat.UserStatDocument;

interface UserStatRepository{
    fun findByUserId(userId: Long): UserStatDocument
    fun updateUserStat(userId: Long, statData: Map<String,Double>, completed: Boolean): UserStatDocument
    fun createUserStatDocument(userId: Long): UserStatDocument
}

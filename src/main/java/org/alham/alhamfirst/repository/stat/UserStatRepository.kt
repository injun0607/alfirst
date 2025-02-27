package org.alham.alhamfirst.repository.stat;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

interface UserStatRepository{
    fun findByUserId(userId: Long): UserStatDocument
    fun updateUserStat(userId: Long, statData: Map<String,Double>): UserStatDocument
    fun createUserStatDocument(userId: Long): UserStatDocument
}

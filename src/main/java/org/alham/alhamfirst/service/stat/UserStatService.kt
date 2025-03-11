package org.alham.alhamfirst.service.stat;

import org.alham.alhamfirst.domain.document.stat.UserStatDocument;
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO;


interface UserStatService {
    fun createUserStatDocument(userId: Long): UserStatDocument
    fun saveStat(userId: Long, statData: Map<String,Double>): UserStatDocument
    fun findByUserId(userId: Long): UserStatDTO
    fun findByEncryptedId(encryptedId: String): UserStatDTO
    fun updateUserStat(userId: Long, statData: Map<String,Double>, completed: Boolean): UserStatDTO

}

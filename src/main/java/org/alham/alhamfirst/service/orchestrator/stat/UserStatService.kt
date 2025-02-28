package org.alham.alhamfirst.service.orchestrator.stat;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;


interface UserStatService {
    fun createUserStatDocument(userId: Long): UserStatDocument
    fun saveStat(userId: Long, statData: Map<String,Double>): UserStatDocument
    fun findByUserId(userId: Long): UserStatDTO
    fun findByEncryptedId(encryptedId: String): UserStatDTO
    fun updateUserStat(userId: Long, statData: Map<String,Double>, completed: Boolean): UserStatDTO

}

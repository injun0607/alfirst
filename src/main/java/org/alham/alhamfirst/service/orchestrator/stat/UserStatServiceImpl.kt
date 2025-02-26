package org.alham.alhamfirst.service.orchestrator.stat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.AlhamCustomErrorLog;
import org.alham.alhamfirst.common.error.AlhamCustomException;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;
import org.alham.alhamfirst.mapper.UserStatMapper;
import org.alham.alhamfirst.repository.stat.UserStatRepository;
import org.alham.alhamfirst.repository.user.UserRepository
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.stereotype.Service;


@Service
@Slf4j
class UserStatServiceImpl(private val userStatRepository: UserStatRepository,
                          private val userStatMapper: UserStatMapper
) :UserStatService{

    override fun saveStat(userId: Long, statData: Map<String,Double>): UserStatDocument{
        TODO()
    }

    override fun findByUserId(userId: Long): UserStatDTO{
        val userStat = userStatRepository.findByUserId(userId)
        return userStatMapper.createStatDTOFromDocument(userStat);
    }

    override fun findByEncryptedId(encryptedId: String): UserStatDTO{
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            val userStat = userStatRepository.findByUserId(userId)
            return userStatMapper.createStatDTOFromDocument(userStat);
        }catch(e: AlhamCustomException){
            AlhamCustomErrorLog(e);
            return UserStatDTO();
        }
    }

    override fun updateUserStat(userId: Long, statData: Map<String,Double>) : UserStatDTO{
        TODO()
    }


}

package org.alham.alhamfirst.service.orchestrator.stat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.AlhamCustomErrorLog;
import org.alham.alhamfirst.common.error.AlhamCustomException;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;
import org.alham.alhamfirst.mapper.UserStatMapper;
import org.alham.alhamfirst.repository.stat.UserStatRepository;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserStatServiceImpl implements UserStatService{

    private final UserStatRepository userStatRepository;
    private final UserStatMapper userStatMapper;
    @Override
    public UserStatDocument saveStat(long userId, Map<String, Double> statData) {
        return null;
    }

    @Override
    public UserStatDTO findByUserId(long userId) {
        UserStatDocument userStat = userStatRepository.findByUserId(userId);
        return userStatMapper.createStatDTOFromDocument(userStat);
    }

    @Override
    public UserStatDTO findByEncryptedId(String encryptedId) {
        try{
            Long userId = CommonUtil.getDecryptedId(encryptedId);
            UserStatDocument userStat = userStatRepository.findByUserId(userId);
            return userStatMapper.createStatDTOFromDocument(userStat);
        }catch(AlhamCustomException e){
            new AlhamCustomErrorLog(e);
            return UserStatDTO.getEmptyUserStat();
        }

    }

    @Override
    public UserStatDTO updateUserStat(long userId, Map<String, Double> statData) {
        return null;
    }

}

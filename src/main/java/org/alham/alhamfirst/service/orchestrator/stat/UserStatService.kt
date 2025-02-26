package org.alham.alhamfirst.service.orchestrator.stat;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;

import java.util.Map;

public interface UserStatService {

    public UserStatDocument saveStat(long userId,  Map<String, Double> statData);
    public UserStatDTO findByUserId(long userId);

    public UserStatDTO findByEncryptedId(String encryptedId);

    public UserStatDTO updateUserStat(long userId, Map<String, Double> statData);


}

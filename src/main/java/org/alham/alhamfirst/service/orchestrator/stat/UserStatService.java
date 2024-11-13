package org.alham.alhamfirst.service.orchestrator.stat;

import org.alham.alhamfirst.document.stat.UserStatDocument;

import java.util.Map;

public interface UserStatService {

    public UserStatDocument saveStat(long userId,  Map<String, Integer> statData);
    public UserStatDocument findByUserId(long userId);

}

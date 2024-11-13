package org.alham.alhamfirst.service.orchestrator.stat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserStatServiceImpl implements UserStatService{
    @Override
    public UserStatDocument saveStat(long userId, Map<String, Integer> statData) {
        return null;
    }

    @Override
    public UserStatDocument findByUserId(long userId) {
        return null;
    }
}

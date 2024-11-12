package org.alham.alhamfirst.service.orchestrator.stat;


import org.alham.alhamfirst.document.stat.StatDocument;

import java.util.Map;

public interface StatService {


    public StatDocument saveStat(long todoIdx,  Map<String, Integer> statData);

    /**
     * desc를 받아서 stat을 산출하는 서비스
     * @param desc
     */
    public String calculateStat(String desc);

    public void updateStat(String stat);

    StatDocument findByTodoIdx(long todoIdx);
}

package org.alham.alhamfirst.service.orchestrator.stat;


import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;

import java.util.List;
import java.util.Map;

public interface TodoStatService {


    public StatDocument saveStat(long todoIdx,  Map<String, Double> statData);

    /**
     * desc를 받아서 stat을 산출하는 서비스
     * @param desc
     */
    public StatDTO calculateStat(String desc);

    public void updateStat(String stat);

    StatDTO findByTodoIdx(long todoIdx);

    List<StatDTO> findListInTodoIdxList(List<Long> todoIdxList);
}

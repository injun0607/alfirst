package org.alham.alhamfirst.service.stat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



public interface StatService {

    /**
     * desc를 받아서 stat을 산출하는 서비스
     * @param desc
     */
    public String calculateStat(String desc);

    public void updateStat(String stat);

}

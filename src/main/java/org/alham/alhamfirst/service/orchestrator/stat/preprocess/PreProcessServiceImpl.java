package org.alham.alhamfirst.service.orchestrator.stat.preprocess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PreProcessServiceImpl implements PreProcessService{

    @Override
    public String preProcess(String desc) {
        StringBuffer sb = new StringBuffer(desc);
        sb.replace(0,sb.length(),escapeDangerousChars(sb.toString()));
        sb.replace(0,sb.length(),removeUnnecessarySpaces(sb.toString()));
        sb.replace(0,sb.length(),normalizeCase(sb.toString()));

        return sb.toString();
    }


    /**
     * Injection 문자열 확인 메소드
     * @param desc
     * @return string
     */
    private String escapeDangerousChars(String desc) {
        return desc.replaceAll("[^a-zA-Z0-9 ]", "");
    }


    /**
     * 불필요한 공백 제거 메소드
     * @param desc
     * @return string
     */
    private String removeUnnecessarySpaces(String desc) {
        return desc.trim();
    }


    /**
     * 일반화 메소드
     * @param desc
     * @return
     */
    private String normalizeCase(String desc) {
        return desc.replaceAll("[^a-zA-Z0-9 ]", "");
    }
}

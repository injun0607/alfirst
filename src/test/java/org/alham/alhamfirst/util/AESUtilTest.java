package org.alham.alhamfirst.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESUtilTest {

    @Test
    public void utilTest() throws Exception {

        //Long 문자열 변환한다음 -> 암호화 후 -> 복호화 후 -> 해당 문자열 Long으로 변환 후 비교



        Long userIdx = 1L;
        String userIdxStr = userIdx.toString();


        String encrypted = AESUtil.encrypt(userIdxStr);
        String decrypted = AESUtil.decrypt(encrypted);

        assertEquals(userIdxStr, decrypted);
        assertEquals(userIdx, Long.parseLong(decrypted));





    }



}
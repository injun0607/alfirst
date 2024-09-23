package org.alham.alhamfirst.service.stat.preprocess;

import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreProcessServiceImplTest {


    @Test
    public void replaceTest(){

        StringBuffer sb = new StringBuffer("abcdefg");
        sb.replace(0,sb.length(),"123");
        assertEquals("123",sb.toString());

    }

}
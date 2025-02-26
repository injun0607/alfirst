package org.alham.alhamfirst.document.stat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class UserStatDocumentTest {


    @Test
    void calulateStat() {
        UserStatDocument userStatDocument = new UserStatDocument();
        userStatDocument.setUserId(1L);
        Map<String,Double> statData = new HashMap<>();
        statData.put("a", 1.0);
        statData.put("b", 2.0);
        statData.put("c", 3.0);
        userStatDocument.setUserStatData(statData);

        Map<String,Double> updateStat = Map.of("a", 2.0, "b", 1.0, "c", 3.0);

        userStatDocument.calculateStat(updateStat);

        assertEquals(3, userStatDocument.getUserStatData().get("a"));
        assertEquals(3, userStatDocument.getUserStatData().get("b"));
        assertEquals(6, userStatDocument.getUserStatData().get("c"));
    }

}
package org.alham.alhamfirst.document.stat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Getter
@Setter
@NoArgsConstructor
public class UserStatDocument {

    @Id
    private String id;
    private Long userId;
    private Map<String,Integer> userStatData = new HashMap<>();

    public UserStatDocument(Long userId, Map<String,Integer> userStatData){
        this.userId = userId;
        this.userStatData = userStatData;
    }

    public void calculateStat(Map<String,Integer> statData){
        statData.forEach((key, value) -> {
            if(userStatData.containsKey(key)){
                userStatData.put(key, userStatData.get(key) + value);
            }else{
                userStatData.put(key, value);
            }
        });
    }


}

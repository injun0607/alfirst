package org.alham.alhamfirst.domain.document.stat;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class UserStatDocument(
        @Id
        var id: String? = null,
        var userId: Long = 0,
        var userStatData: MutableMap<String,Double> = mutableMapOf()
) {

    fun calculateStat(statData: Map<String,Double>) {
        statData.forEach{ (key, value) ->
            if(userStatData.containsKey(key)){
                userStatData[key] = userStatData[key]?.plus(value) ?: 0.0;
            }else{
                userStatData[key] = value;
            }
        }

    }


}

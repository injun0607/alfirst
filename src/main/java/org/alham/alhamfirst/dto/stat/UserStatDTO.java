package org.alham.alhamfirst.dto.stat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class UserStatDTO {
    private String id;
    private Long userIdx;
    private Map<String, Integer> statData = new HashMap<>();

    @Builder
    public UserStatDTO(String id, Long userIdx, Map<String, Integer> statData) {
        this.id = id;
        this.userIdx = userIdx;
        this.statData = statData;
    }

    public static UserStatDTO getEmptyUserStat(){
        return UserStatDTO.builder()
                .id("")
                .userIdx(0L)
                .statData(new HashMap<>())
                .build();
    }
}

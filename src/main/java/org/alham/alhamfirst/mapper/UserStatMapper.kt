package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;
import org.springframework.stereotype.Component;

@Component
public class UserStatMapper {

    public UserStatDTO createStatDTOFromDocument(UserStatDocument userStatDocument){
        return UserStatDTO.builder()
                .id(userStatDocument.getId())
                .userIdx(userStatDocument.getUserId())
                .statData(userStatDocument.getUserStatData())
                .build();
    }

}

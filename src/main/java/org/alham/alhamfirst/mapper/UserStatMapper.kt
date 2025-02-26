package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.UserStatDTO;
import org.springframework.stereotype.Component;

@Component
class UserStatMapper {

    fun createStatDTOFromDocument(userStatDocument: UserStatDocument): UserStatDTO{
        return UserStatDTO(
                id = userStatDocument.id,
                userIdx = userStatDocument.userId,
                statData = userStatDocument.userStatData
        )
    }

}

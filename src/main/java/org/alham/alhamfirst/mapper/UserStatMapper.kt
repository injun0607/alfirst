package org.alham.alhamfirst.mapper;

import org.alham.alhamfirst.domain.document.stat.UserStatDocument
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO

class UserStatMapper {

    fun createStatDTOFromDocument(userStatDocument: UserStatDocument): UserStatDTO {
        return UserStatDTO(
                id = userStatDocument.id,
                userIdx = userStatDocument.userId,
                statData = userStatDocument.userStatData
        )
    }

}

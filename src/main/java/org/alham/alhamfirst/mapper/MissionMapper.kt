package org.alham.alhamfirst.mapper

import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.domain.document.mission.MissionDocument

class MissionMapper {
    fun createDTOFromEntity(mission: MissionDocument): MissionDTO {
        return MissionDTO(
            id = mission.id,
            userId = mission.userId,
            detail = mission.detail,
            missionStatus = mission.missionStatus,
            streak = mission.streak,
            maxStreak = mission.maxStreak,
            regDate = mission.regDate
        )
    }

    fun createEntityFromDTO(missionDTO: MissionDTO): MissionDocument {
        return MissionDocument(
            id = missionDTO.id,
            userId = missionDTO.userId,
            detail = missionDTO.detail,
            missionStatus = missionDTO.missionStatus,
            streak = missionDTO.streak,
            maxStreak = missionDTO.maxStreak,
            regDate = missionDTO.regDate
        )
    }

}
package org.alham.alhamfirst.mapper

import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.domain.entity.mission.Mission

class MissionMapper {
    fun createDTOFromEntity(mission: Mission): MissionDTO {
        return MissionDTO(
            id = mission.id,
            userId = mission.userId,
            detail = mission.detail,
            repeatedStatus = mission.repeatedStatus,
            dayStatus = mission.dayStatus
        )
    }

    fun createEntityFromDTO(missionDTO: MissionDTO): Mission {
        return Mission(
            id = missionDTO.id,
            userId = missionDTO.userId,
            detail = missionDTO.detail,
            repeatedStatus = missionDTO.repeatedStatus,
            dayStatus = missionDTO.dayStatus
        )
    }

}
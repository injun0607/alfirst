package org.alham.alhamfirst.mapper

import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.domain.document.mission.MissionDocument
import org.alham.alhamfirst.domain.document.mission.UserMissionDocument
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO

class MissionMapper {
    fun createDTOFromEntity(mission: MissionDocument): MissionDTO {
        return MissionDTO(
            id = mission.id,
            userId = mission.userId,
            detail = mission.detail,
            missionInfo = mission.missionInfo,
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
            missionInfo = missionDTO.missionInfo,
            streak = missionDTO.streak,
            maxStreak = missionDTO.maxStreak,
            regDate = missionDTO.regDate
        )
    }

    fun createUserMissionDTOFromEntity(userMissionDocument: UserMissionDocument): UserMissionDTO{
        return UserMissionDTO(
            id = userMissionDocument.id,
            userId = userMissionDocument.userId,
            userMissionList = userMissionDocument.userMissionList,
            regDate = userMissionDocument.regDate
        )
    }
    fun createUserMissionEntityFromDTO(userMissionDTO: UserMissionDTO): UserMissionDocument{
        return UserMissionDocument(
            id = userMissionDTO.id,
            userId = userMissionDTO.userId,
            userMissionList = userMissionDTO.userMissionList,
            regDate = userMissionDTO.regDate
        )
    }


}
package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.dto.mission.MissionDTO
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.springframework.stereotype.Service

@Service
class MissionService(val missionRepository: MissionRepository) {

    fun getMissionList(): MissionDTO {
        TODO()
    }

    fun postMissionCompleted(missionId: Long, completed: Boolean): MissionDTO {
        TODO()
    }

    fun registerMission(missionDTO: MissionDTO,encryptedId: String): MissionDTO {
        TODO()
    }

    fun updateMission(missionDTO: MissionDTO): MissionDTO {
        TODO()
    }

    fun deleteMission(missionId: Long): MissionDTO {
        TODO()
    }

}
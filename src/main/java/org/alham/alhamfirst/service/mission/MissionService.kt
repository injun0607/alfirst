package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.error.AlhamCustomErrorLog
import org.alham.alhamfirst.common.error.AlhamCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service

@Service
class MissionService(private val missionRepository: MissionRepository) {

    val log = logger()

    fun getMissionList(): MissionDTO {
        TODO()
    }

    fun postMissionCompleted(missionId: Long, completed: Boolean): MissionDTO {
        TODO()
    }

    fun registerMission(missionDTO: MissionDTO, encryptedId: String): MissionDTO {
        try{
            missionDTO.userId = CommonUtil.getDecryptedId(encryptedId)
            MissionMapper().createEntityFromDTO(missionDTO).let{
                missionRepository.save(it)
                return missionDTO
            }
        }catch(e :Exception){
            log.error("Error in registerMission",e)
            AlhamCustomErrorLog(errorMessage = "Error in registerMission",exception = e)
            throw AlhamCustomException("Error in registerMission",e)
        }
    }

    fun updateMission(missionDTO: MissionDTO): MissionDTO {
        TODO()
    }

    fun deleteMission(missionId: Long): MissionDTO {
        TODO()
    }

}
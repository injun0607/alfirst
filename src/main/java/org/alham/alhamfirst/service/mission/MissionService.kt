package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.stereotype.Service

@Service
class MissionService(private val missionRepository: MissionRepository) {

    val log = logger()
    fun getMissionList(encryptedId: String): List<MissionDTO> {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            return missionRepository.getMissionList(userId)
                .map{MissionMapper().createDTOFromEntity(it)}

        }catch(e: Exception){
            log.error("Error in getMissionList",e)
            AlhamCustomErrorLog(errorMessage = "Error in getMissionList",exception = e)
            throw AlhamCustomException("Error in getMissionList",e)
        }
    }

    fun postMissionCompleted(missionId: Long, completed: Boolean): MissionDTO {
        TODO()
    }

    fun create(missionDTO: MissionDTO, encryptedId: String): MissionDTO {
        try{
            //TODO - Mapper 연결부분 손보기
            missionDTO.userId = CommonUtil.getDecryptedId(encryptedId)
            MissionMapper().createEntityFromDTO(missionDTO).let{
                missionRepository.createMission(it)
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
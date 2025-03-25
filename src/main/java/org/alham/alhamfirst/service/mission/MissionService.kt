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
    fun getMission(missionId: String ,encryptedId: String): MissionDTO{
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            return missionRepository.getMission(missionId,userId)?.let{
                MissionMapper().createDTOFromEntity(it)
            }?: throw AlhamCustomException("No Mission Found")
        } catch(e: Exception){
            log.error("Error in getMission",e)
            AlhamCustomErrorLog(errorMessage = "Error in getMission",exception = e)
            throw AlhamCustomException("Error in getMission",e)
        }
    }

    fun getMissionList(encryptedId: String): List<MissionDTO> {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            val result : List<MissionDTO> = missionRepository.getMissionList(userId)
                .map{MissionMapper().createDTOFromEntity(it)}
            return result
        }catch(e: Exception){
            log.error("Error in getMissionList",e)
            AlhamCustomErrorLog(errorMessage = "Error in getMissionList",exception = e)
            throw AlhamCustomException("Error in getMissionList",e)
        }
    }

    fun postMissionCompleted(missionId: Long, completed: Boolean): MissionDTO {
        TODO()
    }

    fun createMission(missionDTO: MissionDTO, encryptedId: String): MissionDTO {
        try{
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

    fun updateMission(missionDTO: MissionDTO, encryptedId: String): MissionDTO {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            missionDTO.userId = userId
            missionRepository.updateMission(MissionMapper().createEntityFromDTO(missionDTO))
                ?.let { return MissionMapper().createDTOFromEntity(it) }
                ?: throw AlhamCustomException("No Mission Found")
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in updateMission", exception = exception)
            throw Exception("Error in updateMission", exception)
        }
    }

    fun deleteMission(missionId: String, encryptedId: String) {
        try{
            val userId = CommonUtil.getDecryptedId(encryptedId)
            missionRepository.deleteMission(missionId, userId)
                .let { log.info("Mission deleted. delete mission_id = {}, mission_id={}", it?.id,it?.userId) }
        }catch (exception: Exception){
            AlhamCustomErrorLog(exception = exception)
            throw Exception("Error in deleting mission", exception)
        }
    }

}
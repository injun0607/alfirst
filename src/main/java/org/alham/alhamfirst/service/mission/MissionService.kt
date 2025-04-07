package org.alham.alhamfirst.service.mission

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.mapper.MissionMapper
import org.alham.alhamfirst.repository.mission.MissionRepository
import org.alham.alhamfirst.repository.user.UserRepository
import org.alham.alhamfirst.util.CommonUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MissionService(
    private val missionRepository: MissionRepository,
    private val userRepository: UserRepository
) {

    val log = logger()
    fun getMission(missionId: String ,encryptedId: String): MissionDTO{
        val userId = CommonUtil.getDecryptedId(encryptedId)
        return missionRepository.getMission(missionId,userId)?.let{
            MissionMapper().createDTOFromEntity(it)
        }?: throw AlhamCustomException(HttpStatus.NOT_FOUND,"Mission Not Found")
    }

    fun getMissionList(encryptedId: String): List<MissionDTO> {
        val userId = CommonUtil.getDecryptedId(encryptedId)
        return missionRepository.getMissionList(userId)
            .map{MissionMapper().createDTOFromEntity(it)}
    }

    fun createMission(missionDTO: MissionDTO, encryptedId: String): MissionDTO {

        val userId = CommonUtil.getDecryptedId(encryptedId)
        missionDTO.userId = userId

        val user = userRepository.findById(userId)
            .orElseThrow { AlhamCustomException(HttpStatus.NOT_FOUND,"User Not Found") }

        missionRepository.getMissionListCnt(userId).run {
            if(this >= user.userType.maxMissionCnt){
                throw AlhamCustomException(HttpStatus.BAD_REQUEST,"You can't create mission anymore")
            }
        }

        if(user.todayUpdateCnt + 1 > user.userType.maxUpdateCnt){
            throw AlhamCustomException(HttpStatus.BAD_REQUEST,"You can't update mission anymore")
        }else{
            user.todayUpdateCnt++
            userRepository.save(user)
        }


        MissionMapper().createEntityFromDTO(missionDTO).let {
            missionRepository.createMission(it)
            return missionDTO
        }
    }

    fun updateMission(missionDTO: MissionDTO, encryptedId: String): MissionDTO {
            val userId = CommonUtil.getDecryptedId(encryptedId)
            missionDTO.userId = userId
            missionRepository.updateMission(MissionMapper().createEntityFromDTO(missionDTO))
                ?.let {
                    log.info("Mission updated. mission_id =${it.id}, user_id = ${it.userId} details = ${it.detail}")
                    return MissionMapper().createDTOFromEntity(it)
                }
                ?: throw AlhamCustomException(HttpStatus.NOT_FOUND,"No Mission Found")
    }

    fun deleteMission(missionId: String, encryptedId: String) {
        val userId = CommonUtil.getDecryptedId(encryptedId)
        missionRepository.deleteMission(missionId, userId)
            .let { log.info("Mission deleted. delete mission_id = ${missionId}, user_id=${userId}")}
    }

}
package org.alham.alhamfirst.controller.mission

import org.alham.alhamfirst.common.enums.ResponseCode
import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.domain.dto.mission.MissionDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.mission.MissionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mission")
class MissionController (private val jwtToken: UserDTO, private val missionService: MissionService){
    @GetMapping("/{missionId}")
    fun getMission(@PathVariable missionId: String): ResponseEntity<MissionDTO>{
        val userId = jwtToken.id
        try{
            return ResponseEntity.ok(missionService.getMission(missionId,userId))
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in getMission", exception = exception)
            return ResponseEntity.badRequest().build()
        }

    }

    //사용자별 미션 불러오는 리스트
    @GetMapping("/list")
    fun getMissionList(): ResponseEntity<List<MissionDTO>>{
        val userId = jwtToken.id
        try{
            return ResponseEntity.ok(missionService.getMissionList(userId))
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in getMissionList", exception = exception)
            return ResponseEntity.badRequest().build()
        }
    }
    //사용자별 미션 등록
    @PostMapping
    fun createMission(@RequestBody missionDTO: MissionDTO): ResponseEntity<MissionDTO>{
        val userId = jwtToken.id
        try{
            return ResponseEntity.ok(missionService.createMission(missionDTO,userId))
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in createMission", exception = exception)
            return ResponseEntity.badRequest().build()
        }
    }
    //사용자별 미션 완료시 완료처리
    @PostMapping("/{missionId}/{completed}")
    fun postMissionCompleted(@PathVariable missionId: Long, @PathVariable completed: Boolean) : ResponseEntity<MissionDTO>{
        TODO()
    }
    //사용자별 미션 수정
    @PutMapping("/{missionId}")
    fun changeMission(@PathVariable missionId: String, @RequestBody missionDTO: MissionDTO): ResponseEntity<MissionDTO>{
        try{
            missionDTO.id = missionId
            return ResponseEntity.ok(missionService.updateMission(missionDTO, jwtToken.id))
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in changeMission", exception = exception)
            return ResponseEntity.badRequest().build()
        }
    }

    //사용자별 미션 삭제
    @DeleteMapping("/{missionId}")
    fun deleteMission(@PathVariable missionId: String): ResponseCode{
        try{
            missionService.deleteMission(missionId, jwtToken.id)
            return ResponseCode.SUCCESS
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in deleteMission", exception = exception)
            return ResponseCode.FAIL
        }
    }

}
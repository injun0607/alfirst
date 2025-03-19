package org.alham.alhamfirst.controller.mission

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO
import org.alham.alhamfirst.domain.dto.mission.UserMissionListDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.mission.UserMissionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-mission")
class UserMissionController(
    private val userMissionService: UserMissionService,
    private val jwtToken: UserDTO
) {

    @GetMapping("/list")
    fun getUserMissionList(): ResponseEntity<UserMissionListDTO> {
        val userId = jwtToken.id
        try{
            return ResponseEntity.ok(userMissionService.getTodayUserMissionList(userId))
        }catch(exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in getUserMissionList", exception = exception)
            return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/{userMissionId}/{missionId}/{complete}")
    fun completeUserMission(@PathVariable userMissionId: String,
                            @PathVariable missionId: String,
                            @PathVariable complete: Boolean): ResponseEntity<Boolean> {

        val userId = jwtToken.id
        try{
            //업데이트 true로 바뀌고 ->
            //userStat경험치를 변경 해야함
            return ResponseEntity.ok(userMissionService.completeUserMission(userId,userMissionId,missionId,complete))
        }catch (exception: Exception){
            AlhamCustomErrorLog(errorMessage = "Error in completeUserMission", exception = exception)
            return ResponseEntity.badRequest().build()
        }

    }


}
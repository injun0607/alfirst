package org.alham.alhamfirst.controller.mission

import org.alham.alhamfirst.common.exception.AlhamCustomErrorLog
import org.alham.alhamfirst.common.exception.AlhamCustomException
import org.alham.alhamfirst.domain.dto.mission.UserMissionDTO
import org.alham.alhamfirst.domain.dto.mission.UserMissionListDTO
import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.mission.UserMissionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user-mission")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class UserMissionController(
    private val userMissionService: UserMissionService,
    private val jwtToken: UserDTO
) {

    @GetMapping("/list")
    fun getUserMissionList(): ResponseEntity<UserMissionListDTO> {
        val userId = jwtToken.id
        return ResponseEntity.ok(userMissionService.getTodayUserMissionList(userId))
    }

    @PostMapping("/{userMissionId}/{missionId}/{complete}")
    fun completeUserMission(@PathVariable userMissionId: String,
                            @PathVariable missionId: String,
                            @PathVariable complete: Boolean): ResponseEntity<Boolean> {
        val userId = jwtToken.id
        //업데이트 true로 바뀌고 ->
        //userStat경험치를 변경 해야함
        return ResponseEntity.ok(userMissionService.completeUserMission(userId,userMissionId,missionId,complete))
    }


}
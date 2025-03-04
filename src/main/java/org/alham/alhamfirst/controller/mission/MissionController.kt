package org.alham.alhamfirst.controller.mission

import org.alham.alhamfirst.dto.mission.MissionDTO
import org.alham.alhamfirst.dto.user.UserDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mission")
class MissionController (private val jwtToken: UserDTO){

    //사용자별 미션 불러오는 리스트
    @GetMapping("/list")
    fun getMissionList() : ResponseEntity<MissionDTO>{

        TODO()
    }

    //사용자별 미션 완료시 완료처리
    @PostMapping("/{missionId}/{completed}")
    fun postMissionCompleted(@PathVariable missionId: Long, @PathVariable completed: Boolean) : ResponseEntity<MissionDTO>{
        TODO()
    }

    //사용자별 미션 등록

    //사용자별 미션 수정

    //사용자별 미션 삭제



}
package org.alham.alhamfirst.controller.user

import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.OrchestratorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController (
    private val orchestratorService: OrchestratorService,
    private val jwtToken: UserDTO
){

    @GetMapping
    fun getUserInfo():ResponseEntity<UserDTO>{
        try{
            val encryptedId = jwtToken.id
            return ResponseEntity.ok(orchestratorService.getUserInfo(encryptedId))
        }catch (e: Exception){
            return ResponseEntity.badRequest().build()
        }

    }


}
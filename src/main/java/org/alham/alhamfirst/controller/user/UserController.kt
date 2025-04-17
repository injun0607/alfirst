package org.alham.alhamfirst.controller.user

import org.alham.alhamfirst.domain.dto.user.UserDTO
import org.alham.alhamfirst.service.OrchestratorService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController (
    private val orchestratorService: OrchestratorService){

    @GetMapping
    fun getUserInfo(@AuthenticationPrincipal principal: UserDTO):ResponseEntity<UserDTO>{
        try{
            val encryptedId = principal.id
            return ResponseEntity.ok(orchestratorService.getUserInfo(encryptedId))
        }catch (e: Exception){
            return ResponseEntity.badRequest().build()
        }

    }


}
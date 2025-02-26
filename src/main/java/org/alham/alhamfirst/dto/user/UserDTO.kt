package org.alham.alhamfirst.dto.user;

import lombok.*;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.util.AESUtil;

import java.util.HashMap;
import java.util.Map;

//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@Builder
//@AllArgsConstructor
data class UserDTO(
        var id : String = "",
        var name : String ="",
        var age : Int = 0,
        var userType : UserType = UserType.BASIC,
        var email : String = "",
        var statData : MutableMap<String,Int> = mutableMapOf()
) {


    fun getEmptyUser() :  UserDTO{
        return UserDTO();
    }

    fun getIdDecrypt() : Long{
        return AESUtil.decrypt(this.id).toLong();
    }

}

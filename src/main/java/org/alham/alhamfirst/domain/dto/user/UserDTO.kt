package org.alham.alhamfirst.domain.dto.user;

import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.util.AESUtil;

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
        var statData : MutableMap<String,Double> = mutableMapOf()
) {


    fun getEmptyUser() : UserDTO {
        return UserDTO();
    }

    fun getIdDecrypt() : Long{
        return AESUtil.decrypt(this.id).toLong();
    }

}

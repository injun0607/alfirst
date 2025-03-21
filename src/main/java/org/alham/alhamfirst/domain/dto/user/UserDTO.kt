package org.alham.alhamfirst.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.util.AESUtil;

data class UserDTO(
        var id : String = "",
        var uuid : String = "",
        var oauthProvider : String = "",
        var oauthId : String = "",
        var name : String ="",
        var age : Int = 0,
        var userType : UserType = UserType.BASIC,
        var email : String = "",
        var statData : MutableMap<String,Double> = mutableMapOf()
) {

    @JsonIgnore
    fun getEmptyUser() : UserDTO {
        return UserDTO();
    }

    @JsonIgnore
    fun getIdDecrypt() : Long{
        return AESUtil.decrypt(this.id).toLong();
    }

}

package org.alham.alhamfirst.dto.user;

import lombok.*;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.util.AESUtil;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {


    private String id;
    private String name;
    private int age;
    private UserType userType;
    private String email;
    private Map<String, Integer> statData = new HashMap<>();

    public static UserDTO getEmptyUser(){
        return new UserDTO();
    }

    public long getIdDecrypt() {
        return Long.parseLong(AESUtil.decrypt(this.id));
    }

}

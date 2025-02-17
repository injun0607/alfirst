package org.alham.alhamfirst.dto.user;

import lombok.*;
import org.alham.alhamfirst.common.enums.UserType;
import org.alham.alhamfirst.util.AESUtil;

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


    public static UserDTO getEmptyUser(){
        return new UserDTO();
    }

    public long getIdDecrypt() {
        return Long.parseLong(AESUtil.decrypt(this.id));
    }

}

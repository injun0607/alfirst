package org.alham.alhamfirst.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.alhamfirst.common.enums.UserType;

@Entity
@Table(name = "al_user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="al_user_id")
        var id : Long? = null,

        var name : String = "",
        var age :Int = 0,
        var userType : UserType = UserType.BASIC,
        var email : String ="",

) {

    /**
     * 연관 관계를 위한 유저 생성
     */
    fun createTempUser(userIdx : Long) : User {
        return User(id = userIdx)
    }

}

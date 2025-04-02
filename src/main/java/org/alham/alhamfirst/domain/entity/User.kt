package org.alham.alhamfirst.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.alhamfirst.common.enums.UserType;

@Entity
@Table(
    name = "al_user",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["uuid"]),
        UniqueConstraint(columnNames = ["oauthProvider", "oauthId"])
    ]
)
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="al_user_id")
        var id: Long? = null,

        @Column(name="uuid", unique = true)
        var uuid: String = "",
        var oauthProvider: String = "",
        var oauthId: String = "",
        var name: String = "",
        var age: Int = 0,
        var userType: UserType = UserType.BASIC,
        var email: String ="",
        var todayUpdateCnt: Int = 0,

) {

    fun createTempUser(userIdx : Long) : User {
        return User(id = userIdx)
    }

}

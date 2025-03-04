package org.alham.alhamfirst.entity.mission

import jakarta.persistence.*
import org.alham.alhamfirst.common.enums.DayStatus
import org.alham.alhamfirst.common.enums.RepeatedStatus
import org.alham.alhamfirst.entity.User

@Entity(name = "al_mission")
class Mission(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="mission_id")
    val id : Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "al_user_id")
    var user : User = User(),

    var detail: String = "",
    var repeatedStatus: RepeatedStatus = RepeatedStatus.DAILY,
    var dayStatus: DayStatus = DayStatus.NONE
) {
}
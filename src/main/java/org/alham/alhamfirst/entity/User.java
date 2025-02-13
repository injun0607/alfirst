package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.alhamfirst.common.enums.UserType;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "al_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "al_user_id")
    private Long id;

    private String name;

    private int age;

    private UserType userType = UserType.BASIC;

    private String email;

    @Deprecated
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

    private User(Long id){
        this.id = id;
    }

    /**
     * 연관 관계를 위한 임시 유저 생성
     * @param userIdx
     * @return
     */
    public static User createTempUser(Long userIdx){
        return new User(userIdx);
    }

    @Builder
    public User(Long id, String name, int age, UserType userType, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userType = userType;
        this.email = email;
    }



}

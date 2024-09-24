package org.alham.alhamfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "al_share")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_id")
    private long id;

    private String title;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private int likeCnt;




}

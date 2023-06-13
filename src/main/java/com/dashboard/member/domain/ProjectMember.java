package com.dashboard.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;

@Builder
@Entity
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pjtMemId;

    @Column
    private String pjtId;

    @Column
    private String memId;

}

package com.dashboard.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectMember {

    @Id
    private String pjtMemId;

    @Column
    private String pjtId;

    @Column
    private String memId;

}

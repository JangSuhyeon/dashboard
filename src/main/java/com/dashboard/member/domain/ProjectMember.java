package com.dashboard.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProjectMember {

    @Id
    private String pjtMemId;

    @Column
    private String pjtId;

    @Column
    private String memId;

    @Column
    private String leaderYn;

}

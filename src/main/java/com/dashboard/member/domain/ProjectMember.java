package com.dashboard.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;

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

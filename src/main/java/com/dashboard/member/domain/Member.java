package com.dashboard.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @Id
    private String memId;

    @Column
    private String memNm;

    @Column
    private String memImg;

}

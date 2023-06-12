package com.dashboard.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Project {

    @Id
    private String pjtId;

    @Column
    private String pjtNm;

    @Column
    private Date startDt;

    @Column
    private Date endDt;

    @Column
    private String status;

    @Column
    private int progress;

    @Column
    private String content;

    @Column
    private Date regDt;

    @Column
    private Date modDt;

    @Column
    private String useYn;

}

package com.dashboard.project.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

}

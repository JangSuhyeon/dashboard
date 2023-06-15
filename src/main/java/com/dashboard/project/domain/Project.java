package com.dashboard.project.domain;

import com.dashboard.project.domain.dto.ProjectResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}

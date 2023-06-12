package com.dashboard.project.domain.dto;

import lombok.Getter;

@Getter
public class ProjectRequestDTO {

    private String pjtNm;
    private String startDt;
    private String endDt;
    private String status;
    private int progress;
    private String content;

}

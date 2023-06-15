package com.dashboard.project.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
public class ProjectRequestDTO {

    // project
    private String pjtId;
    private String pjtNm;
    private Date startDt;
    private Date endDt;
    private String status;
    private int progress;
    private String content;

    // member
    List<String> memIdList;

}

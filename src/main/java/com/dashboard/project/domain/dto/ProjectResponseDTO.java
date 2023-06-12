package com.dashboard.project.domain.dto;

import com.dashboard.member.domain.dto.MemberResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
@Builder
public class ProjectResponseDTO {

    // Project
    private String pjtId;
    private String pjtNm;
    private String startDt;
    private String endDt;
    private String status;
    private int progress;
    private String content;
    private Date regDt;
    private Date modDt;
    private String useYn;

    // Member
    private List<MemberResponseDTO> memberList;

    // Code
    private String statusNm;

}

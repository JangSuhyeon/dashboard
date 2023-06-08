package com.dashboard.project.domain.dto;

import com.dashboard.member.domain.Member;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
public class ProjectResponseDTO {

    // Project
    private String pjtId;
    private String pjtNm;
    private Date startDt;
    private Date endDt;
    private String status;
    private int progress;
    private Date regDt;
    private Date modDt;
    private String useYn;

    //Member
    private List<MemberResponseDTO> memberList;

}

package com.dashboard.member.domain.dto;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public class MemberResponseDTO {

    private String memId;
    private String memNm;
    private String memImg;

}

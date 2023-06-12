package com.dashboard.member.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDTO {

    private String memId;
    private String memNm;
    private String memImg;

}

package com.dashboard.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class MemberResponseDTO {

    private String memId;
    private String memNm;
    private String memImg;

}

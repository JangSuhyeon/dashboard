package com.dashboard.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@Getter
@ToString
public class MemberResponseDTO {

    private String memId;
    private String memNm;
    private String memImg;
    private String leaderYn;

}

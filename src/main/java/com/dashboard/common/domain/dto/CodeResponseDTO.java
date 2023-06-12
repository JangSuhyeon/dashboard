package com.dashboard.common.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CodeResponseDTO {

    private String code;
    private String codeName;

}

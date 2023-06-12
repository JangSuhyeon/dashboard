package com.dashboard.common.service;

import com.dashboard.common.domain.Code;
import com.dashboard.common.domain.dto.CodeResponseDTO;
import com.dashboard.common.repository.CodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CodeService {

    private final CodeRepository codeRepository;

    public List<CodeResponseDTO> findAllByGroupCode(String status) {
        List<CodeResponseDTO> cdResDtoList = new ArrayList<>();

        List<Code> codeList = codeRepository.findAllByGroupCode(status);
        for (Code code : codeList) {
            CodeResponseDTO cdResDto = CodeResponseDTO.builder()
                    .code(code.getCode())
                    .codeName(code.getCodeName())
                    .build();
            cdResDtoList.add(cdResDto);
        }
        return cdResDtoList;
    }
}

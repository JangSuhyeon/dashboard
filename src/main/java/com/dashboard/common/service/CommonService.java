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
public class CommonService {

    private final CodeRepository codeRepository;

    public List<CodeResponseDTO> findAllByGroupCode(String groupCode) {
        List<CodeResponseDTO> cdResDtoList = new ArrayList<>();

        List<Code> codeList = codeRepository.findAllByGroupCode(groupCode);
        for (Code code : codeList) {
            CodeResponseDTO cdResDto = code.toDto();
            cdResDtoList.add(cdResDto);
        }
        return cdResDtoList;
    }
}

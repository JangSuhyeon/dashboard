package com.dashboard.common.controller;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import com.dashboard.common.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/code")
public class CommonController {

    private final CommonService commonService;

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<List<CodeResponseDTO>> findByGroupCode(@RequestParam("groupCode") String groupCode){

        List<CodeResponseDTO> cdResDtoList = commonService.findAllByGroupCode(groupCode);

        return ResponseEntity.ok().body(cdResDtoList);
    }

}

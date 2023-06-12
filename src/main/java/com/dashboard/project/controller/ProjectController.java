package com.dashboard.project.controller;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import com.dashboard.common.service.CodeService;
import com.dashboard.project.domain.dto.ProjectRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectController {

    private final CodeService codeService;

    @GetMapping("/create")
    public String goToProjectCreate(Model model){

        // 상태값 목록 조회
        List<CodeResponseDTO> cdResDtoList = codeService.findAllByGroupCode("STATUS");

        model.addAttribute("statusList", cdResDtoList);

        return "page/project/create";
    }

    @PostMapping("/create")
    public String createProject(@RequestBody ProjectRequestDTO pjtReqDto){
        return ""
    }

}

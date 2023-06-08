package com.dashboard.main;

import com.dashboard.project.domain.dto.ProjectResponseDTO;
import com.dashboard.project.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("")
public class MainController {

    private final ProjectService projectService;

    @GetMapping("")
    public String goToDashboard(){
        List<ProjectResponseDTO> pjtResDTOList = new ArrayList<>();

        // 프로젝트 목록 조회
        pjtResDTOList = projectService.findAll();
        System.out.println(pjtResDTOList);

        return "page/dashboard";
    }

}

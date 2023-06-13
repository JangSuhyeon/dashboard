package com.dashboard.main;

import com.dashboard.project.domain.dto.ProjectResponseDTO;
import com.dashboard.project.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("")
public class MainController {

    private final ProjectService projectService;

    @GetMapping("")
    public String goToDashboard(@PageableDefault(page = 0, size = 10, sort = "regDt", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        // 프로젝트 목록 조회
        Page<ProjectResponseDTO> pjtResDTOList = projectService.findAll(pageable);

        model.addAttribute("projectList", pjtResDTOList);
        model.addAttribute("totalPages", pjtResDTOList.getTotalPages());
        model.addAttribute("currentPage", pjtResDTOList.getNumber() + 1);

        return "page/dashboard";
    }

}

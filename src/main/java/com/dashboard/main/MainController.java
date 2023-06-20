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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

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
        model.addAttribute("firstConIdx", pjtResDTOList.getNumber() * pjtResDTOList.getSize() + 1);

        return "page/dashboard";
    }

    @GetMapping("/login/oauth2/callback/google")
    public String googleRedirect(@RequestParam HashMap<String,Object> response) {

        for (String s : response.keySet()) {
            System.out.println(s);
            System.out.println(response.get(s));
        }

        return "redirect:/";

    }

}

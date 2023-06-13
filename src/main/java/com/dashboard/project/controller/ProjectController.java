package com.dashboard.project.controller;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import com.dashboard.common.service.CodeService;
import com.dashboard.member.MemberService;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.project.domain.dto.ProjectRequestDTO;
import com.dashboard.project.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectController {

    private final CodeService codeService;

    private final ProjectService projectService;

    private final MemberService memberService;

    @GetMapping("/create")
    public String goToProjectCreate(Model model){

        // 상태값 목록 조회
        List<CodeResponseDTO> cdResDtoList = codeService.findAllByGroupCode("STATUS");

        // 멤버 목록 조회
        List<MemberResponseDTO> memResDtoList = memberService.findAll();

        model.addAttribute("statusList", cdResDtoList);
        model.addAttribute("memberList", memResDtoList);

        return "page/project/create";
    }

    @ResponseBody
    @PostMapping(value = "/create")
    public ResponseEntity<String> createProject(@RequestBody ProjectRequestDTO pjtReqDto){
        ResponseEntity<String> response;

        try {
            projectService.save(pjtReqDto);
            response = ResponseEntity.status(HttpStatus.CREATED).body("생성성공");
        }catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
        }

        return response;
    }

}

package com.dashboard.project.controller;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import com.dashboard.common.service.CodeService;
import com.dashboard.member.service.MemberService;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.project.domain.dto.ProjectRequestDTO;
import com.dashboard.project.domain.dto.ProjectResponseDTO;
import com.dashboard.project.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    // 신규 프로젝트 생성 페이지로 이동
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

    // 신규 프로젝트 생성
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

    // 프로젝트 상세 정보 조회
    @GetMapping("/{pjtId}")
    public String goToProjectDetail(@PathVariable("pjtId") String pjtId, Model model){

        // 프로젝트 상세 정보 조회
        ProjectResponseDTO pjtResDto = projectService.findById(pjtId);

        // 없을 경우에 예외발생 문구 전송?

        model.addAttribute("project", pjtResDto);

        return "page/project/detail";
    }

}

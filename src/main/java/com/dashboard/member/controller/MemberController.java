package com.dashboard.member.controller;

import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<List<MemberResponseDTO>> findAll(){

        List<MemberResponseDTO> memResDtoList = memberService.findAll();

        return ResponseEntity.ok().body(memResDtoList);
    }

}

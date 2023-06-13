package com.dashboard.project.service;

import com.dashboard.common.domain.Code;
import com.dashboard.common.repository.CodeRepository;
import com.dashboard.member.domain.Member;
import com.dashboard.member.domain.ProjectMember;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.member.repository.MemberRepository;
import com.dashboard.member.repository.ProjectMemberRepository;
import com.dashboard.project.domain.Project;
import com.dashboard.project.domain.dto.ProjectRequestDTO;
import com.dashboard.project.domain.dto.ProjectResponseDTO;
import com.dashboard.project.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final MemberRepository memberRepository;

    private final CodeRepository codeRepository;

    private final ProjectMemberRepository projectMemberRepository;

    public Page<ProjectResponseDTO> findAll(Pageable pageable) {
        List<ProjectResponseDTO> pjtResDtoList = new ArrayList<>();

        // 프로젝트 목록 조회
        Page<Project> pjtList = projectRepository.findAll(pageable);

        for (Project project : pjtList) {

            String pjtId = project.getPjtId();
            String statusCd = project.getStatus();

            // 프로젝트 멤버 조회
            List<MemberResponseDTO> memResDtoList = new ArrayList<>();
            List<Member> memList = memberRepository.findAllByPjtId(pjtId);
            for (Member member : memList) {
                // Entity -> DTO
                MemberResponseDTO memResDto = MemberResponseDTO.builder()
                        .memId(member.getMemId())
                        .memNm(member.getMemNm())
                        .memImg(member.getMemImg())
                        .build();
                memResDtoList.add(memResDto);
            }

            // 코드 상태값 조회
            Code code = codeRepository.findCodeNameByGroupCodeAndCode("STATUS", statusCd);

            // Entity -> DTO
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ProjectResponseDTO pjtResDto = ProjectResponseDTO.builder()
                    .pjtId(project.getPjtId())
                    .pjtNm(project.getPjtNm())
                    .startDt(dateFormat.format(project.getStartDt()))
                    .endDt(dateFormat.format(project.getEndDt()))
                    .status(project.getStatus())
                    .progress(project.getProgress())
                    .regDt(project.getRegDt())
                    .modDt(project.getModDt())
                    .useYn(project.getUseYn())
                    .memberList(memResDtoList)
                    .statusNm(code.getCodeName())
                    .build();

            pjtResDtoList.add(pjtResDto);
        }

        return new PageImpl<>(pjtResDtoList, pageable, pjtList.getTotalElements());
    }

    public void save(ProjectRequestDTO pjtReqDto) {

        // pjtId 시퀀스 생성
        String pjtId = codeRepository.callGenerateProjectIdFunction("PJT");

        Project project = Project.builder()
                .pjtId(pjtId)
                .pjtNm(pjtReqDto.getPjtNm())
                .startDt(pjtReqDto.getStartDt())
                .endDt(pjtReqDto.getEndDt())
                .status(pjtReqDto.getStatus())
                .progress(pjtReqDto.getProgress())
                .content(pjtReqDto.getContent())
                .regDt(new Date())
                .useYn("Y")
                .build();

        // 신규 프로젝트 생성
        projectRepository.save(project);

        List<String> memIdList = pjtReqDto.getMemIdList();
        for (String memId : memIdList) {

            // pjtMemId 시퀀스 생성
            String pjtMemId = codeRepository.callGenerateProjectIdFunction("PJM");

            ProjectMember projectMember = ProjectMember.builder()
                    .pjtMemId(pjtMemId)
                    .pjtId(pjtId)
                    .memId(memId)
                    .leaderYn("N")
                    .build();

            // Project <-> Member 생성
            projectMemberRepository.save(projectMember);
        }
    }
}

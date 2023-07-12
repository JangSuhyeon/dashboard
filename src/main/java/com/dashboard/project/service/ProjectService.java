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

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            List<Tuple> memTuList = memberRepository.findAllByPjtId(pjtId);
            List<MemberResponseDTO> memResDtoList = memTuList.stream()
                    .map(tuple -> {
                        Member member = tuple.get(0,Member.class);
                        String leaderYn = tuple.get(1,String.class);

                        MemberResponseDTO memResDto = member.toDto();
                        memResDto.setLeaderYn(leaderYn);

                        return memResDto;
                    })
                    .collect(Collectors.toList());

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
                .build();

        // 신규 프로젝트 생성
        projectRepository.save(project);

        String leaderMemId = pjtReqDto.getLeaderMemId();
        List<String> memIdList = pjtReqDto.getMemIdList();
        for (String memId : memIdList) {

            // pjtMemId 시퀀스 생성
            String pjtMemId = codeRepository.callGenerateProjectIdFunction("PJM");
            ProjectMember projectMember = ProjectMember.builder()
                    .pjtMemId(pjtMemId)
                    .pjtId(pjtId)
                    .memId(memId)
                    .leaderYn(leaderMemId.equals(memId) ? "Y" : "N")
                    .regDt(new Date())
                    .build();

            // Project <-> Member 생성
            projectMemberRepository.save(projectMember);
        }
    }

    public ProjectResponseDTO findById(String pjtId) {
        ProjectResponseDTO pjtResDto = new ProjectResponseDTO();
        List<MemberResponseDTO> memResDtoList = new ArrayList<>();

        Project project = projectRepository.findById(pjtId).orElse(null);

        if (project != null){
            // 프로젝트 멤버 조회
            List<Tuple> memTupList = memberRepository.findAllByPjtId(pjtId);
            memResDtoList = memTupList.stream()
                    .map(tuple -> {
                        Member member = tuple.get(0,Member.class);
                        String leaderYn = tuple.get(1,String.class);

                        MemberResponseDTO memResDto = member.toDto();
                        memResDto.setLeaderYn(leaderYn);

                        return memResDto;
                    })
                    .collect(Collectors.toList());

            // 코드 상태값 조회
            Code code = codeRepository.findCodeNameByGroupCodeAndCode("STATUS", project.getStatus());

            // Entity -> DTO
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            pjtResDto = ProjectResponseDTO.builder()
                    .pjtId(project.getPjtId())
                    .pjtNm(project.getPjtNm())
                    .startDt(dateFormat.format(project.getStartDt()))
                    .endDt(dateFormat.format(project.getEndDt()))
                    .status(project.getStatus())
                    .progress(project.getProgress())
                    .content(project.getContent())
                    .regDt(project.getRegDt())
                    .modDt(project.getModDt())
                    .memberList(memResDtoList)
                    .statusNm(code.getCodeName())
                    .build();
        }

        return pjtResDto;
    }

    public void deleteById(String pjtId) {
        projectRepository.deleteById(pjtId);
    }

    @Transactional
    public void update(ProjectRequestDTO pjtReqDto) {

        Project project = projectRepository.findById(pjtReqDto.getPjtId()).orElse(null);
        if(pjtReqDto.getPjtNm() != null) project.setPjtNm(pjtReqDto.getPjtNm());
        if(pjtReqDto.getStartDt() != null) project.setStartDt(pjtReqDto.getStartDt());
        if(pjtReqDto.getEndDt() != null) project.setEndDt(pjtReqDto.getEndDt());
        if(pjtReqDto.getStatus() != null) project.setStatus(pjtReqDto.getStatus());
        if(pjtReqDto.getProgress() == 0) project.setProgress(pjtReqDto.getProgress());
        if(pjtReqDto.getContent() != null) project.setContent(pjtReqDto.getContent());
        project.setModDt(new Date());

    }
}

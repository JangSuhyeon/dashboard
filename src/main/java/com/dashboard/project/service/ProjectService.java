package com.dashboard.project.service;

import com.dashboard.common.domain.Code;
import com.dashboard.common.repository.CodeRepository;
import com.dashboard.member.domain.Member;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.member.repository.MemberRepository;
import com.dashboard.project.domain.Project;
import com.dashboard.project.domain.dto.ProjectResponseDTO;
import com.dashboard.project.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final MemberRepository memberRepository;

    private final CodeRepository codeRepository;

    private final EntityManager entityManager;

    public Page<ProjectResponseDTO> findAll(Pageable pageable) {
        List<ProjectResponseDTO> pjtResDtoList = new ArrayList<>();

        // 프로젝트 목록 조회
        Page<Project> pjtList = projectRepository.findAll(pageable);

        for (Project project : pjtList) {

            // 영속성 컨텍스트 초기화
            entityManager.clear();

            String pjtId = project.getPjtId();

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
            String statusCd = project.getStatus();
            Code code = codeRepository.findByGroupCodeAndCode("STATUS", statusCd);

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
}

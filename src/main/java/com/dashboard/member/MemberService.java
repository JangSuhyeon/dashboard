package com.dashboard.member;

import com.dashboard.member.domain.Member;
import com.dashboard.member.domain.dto.MemberResponseDTO;
import com.dashboard.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponseDTO> findAll() {
        List<MemberResponseDTO> memResDtoList = new ArrayList<>();

        // 멤버 목록 조회
        List<Member> memList = memberRepository.findAll();

        for (Member member : memList) {
            // Entity - > DTO
            MemberResponseDTO memResDto = MemberResponseDTO.builder()
                    .memId(member.getMemId())
                    .memNm(member.getMemNm())
                    .memImg(member.getMemImg())
                    .build();
            memResDtoList.add(memResDto);
        }

        return memResDtoList;
    }
}

package com.dashboard.member.domain;

import com.dashboard.member.domain.dto.MemberResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
@Entity
public class Member {

    @Id
    private String memId;

    @Column
    private String memNm;

    @Column
    private String memImg;

    public MemberResponseDTO toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MemberResponseDTO.class);
    }

}

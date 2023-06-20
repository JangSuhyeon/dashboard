package com.dashboard.member.domain;

import com.dashboard.member.domain.dto.MemberResponseDTO;
import lombok.Getter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

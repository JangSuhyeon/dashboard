package com.dashboard.common.domain;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import lombok.Getter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Entity
public class Code {

    @Id
    private String cdId;

    @Column
    private String groupCode;

    @Column
    private String code;

    @Column
    private String codeName;

    public CodeResponseDTO toDto(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CodeResponseDTO.class);
    }

}

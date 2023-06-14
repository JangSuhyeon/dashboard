package com.dashboard.common.domain;

import com.dashboard.common.domain.dto.CodeResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

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

package com.dashboard.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Code {

    @Id
    private String groupCode;

    @Column
    private String code;

    @Column
    private String codeName;

}

package com.dashboard.user.domain;

import com.dashboard.user.domain.dto.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Users(Long id, String name, String email, String password, Role role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users update(String name){
        this.name = name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}

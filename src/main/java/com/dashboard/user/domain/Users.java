package com.dashboard.user.domain;

import com.dashboard.user.domain.dto.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String lastLoginDate;

    @Builder
    public Users(Long id, String name, String email, String password, Role role){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastLoginDate = formattedDateTime;
    }

    public Users update(String name){
        this.name = name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public void updateLastLoginDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        this.lastLoginDate = formattedDateTime;
    }

}

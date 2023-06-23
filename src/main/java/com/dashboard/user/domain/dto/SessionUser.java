package com.dashboard.user.domain.dto;

import com.dashboard.user.domain.Users;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SessionUser {

    private final String name;
    private final String email;

    public SessionUser(Users user){
        this.name = user.getName();
        this.email = user.getEmail();
    }

}

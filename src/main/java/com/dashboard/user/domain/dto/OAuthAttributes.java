package com.dashboard.user.domain.dto;

import com.dashboard.user.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Builder
public class OAuthAttributes {

    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }

}

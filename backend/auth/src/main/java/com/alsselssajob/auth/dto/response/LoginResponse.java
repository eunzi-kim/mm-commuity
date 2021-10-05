package com.alsselssajob.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginResponse {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("token")
    private String token;

    // 생성자 자체
    @Builder
    public LoginResponse(final String userId, final String nickName, final String userName, final String token) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.token = token;
    }

    public String userId() {
        return userId;
    }

    public String nickName() {
        return nickName;
    }

    public String userName() {
        return userName;
    }

    public String token() {
        return token;
    }
}

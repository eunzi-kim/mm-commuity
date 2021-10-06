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
    @JsonProperty("image")
    private String image;

    // 생성자 자체
    @Builder
    public LoginResponse(final String userId, final String nickName, final String userName, final String token, final String image) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.token = token;
        this.image = image;
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

    public String image() {
        return image;
    }
}

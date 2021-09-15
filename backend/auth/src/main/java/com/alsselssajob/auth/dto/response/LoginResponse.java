package com.alsselssajob.auth.dto.response;

import lombok.Builder;

public class LoginResponse {
    private String userId;
    private String nickName;
    private String userName;

    @Builder
    public LoginResponse(final String userId, final String nickName, final String userName) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
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
}

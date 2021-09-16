package com.alsselssajob.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginRequest {
    //.get 표시 대신
    @JsonProperty("id")
    private String id;
    @JsonProperty("password")
    private String password;

    @Builder
    public LoginRequest(final String id, final String password) {
        this.id = id;
        this.password = password;
    }

    public String id() {
        return id;
    }

    public String password() {
        return password;
    }
}

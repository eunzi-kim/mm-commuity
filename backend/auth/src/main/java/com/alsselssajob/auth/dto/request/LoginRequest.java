package com.alsselssajob.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
// 레이어간 통신 용도로 오고가는 객체.
// 데이터를 오브젝트로 변환하는 객체.

// 계층간 데이터 교환을 위한 객체(자바 빈스)
// 즉, 데이터를 Service나 Controller등으로 보낼 때 사용하는 객체
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

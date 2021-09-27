package com.alsselssajob.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
// 레이어간 통신 용도로 오고가는 객체.
// 데이터를 오브젝트로 변환하는 객체.

// 계층간 데이터 교환을 위한 객체(자바 빈스)
// 즉, 데이터를 Service나 Controller등으로 보낼 때 사용하는 객체
@NoArgsConstructor
public class LogoutRequest {
    //.get 표시 대신
    @JsonProperty("userId")
    private String userId;

    @Builder
    public LogoutRequest(final String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }

}

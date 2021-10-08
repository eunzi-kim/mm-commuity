package com.alsselssajob.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
// DTO가 존재하는 이유는, DB의 테이블상의 컬럼과 프로그램 클래스의 변수가 서로 같음을 알려주기 위해서다.
// DTO가 없으면 서로 같은 단어(ex. userId)일지라도 컴파일이 되면 바이트코드로 변환되어 서로 다른 메모리가 되어버리기 때문에..

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

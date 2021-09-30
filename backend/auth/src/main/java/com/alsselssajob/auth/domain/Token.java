package com.alsselssajob.auth.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// VO : 특정 비즈니스 값을 담는 객체.
// 유효성 검증.
// DB에 있는 것들.


//@Entity : 테이블과 매핑될 클래스 임을 알려주는 어노테이션.
@Entity
@NoArgsConstructor
public class Token {

    // "멤버변수"
    //@Id : 해당 테이블의 PK 설정
    // 서버 자체에서 DB저장하기위한 30이란길이를 미리 검증해서 데이터를 받는다.
    // DB에 막상 넣을 때 오류가 나는 것을 방지하기 위해.
    @Id
    @Column(name = "userId", length = 30)
    private String userId;
    @Column(name = "token", unique = true, nullable = false, length = 30)
    private String token;
    @Column(name = "isActive")
    private Boolean isActive;

    //Builder는 파라미터가 있는 생성자를 대체할 수 있다.
    //즉, 객체 생성 시 값 세팅
    @Builder
    public Token(final String userId, final String token, final Boolean isActive) {

        this.userId = userId;
        this.token = token;
        this.isActive = isActive;
    }

    // "Getters"
    public String userId() {
        return userId;
    }

    public String token() {
        return token;
    }

    public Boolean isActive() {
        return isActive;
    }

    //isActive를 false로바꿔주는 메서드
    public void switchIsActiveToFalse(){
        this.isActive = false;
    }

}





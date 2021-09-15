package com.alsselssajob.auth.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Token {

    // "멤버변수"
    @Id
    @Column(name = "user_id", length = 30)
    private String userId;
    @Column(name = "token", unique = true, nullable = false, length = 30)
    private String token;
    @Column(name = "is_active")
    private Boolean isActive;

    //builder는 파라미터가 있는 생성자를 대체할 수 있다.
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
}


package com.alsselssajob.auth.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

//push 전에 반드시 All test 해보기!
//ctrl + shift + T
//메서드 이름이 길어지는 경우가 생겨서 snake case 사용
class TokenTest {
    private Token token;

    // token객체 자동 생성
    @BeforeEach
    void setUp() {
        token = Token.builder()
                .userId("asdfadfglgsdlfkj234sdf")
                .token("saoggerng57kgsfhg")
                .isActive(true)
                .build();
    }

    @DisplayName("Token 클래스 / Construct 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(token).isNotNull(),
                () -> assertThat(token).isExactlyInstanceOf(Token.class)

        );
    }

    @DisplayName("Token 클래스 / Getters 테스트")
    @Test
    void getters_test() {
        assertAll(
                () -> assertThat(token.userId()).isEqualTo("asdfadfglgsdlfkj234sdf"),
                () -> assertThat(token.token()).isEqualTo("saoggerng57kgsfhg"),
                () -> assertThat(token.isActive()).isTrue()
        );

    }
}
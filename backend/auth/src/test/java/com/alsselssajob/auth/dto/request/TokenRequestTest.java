package com.alsselssajob.auth.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenRequestTest {

    private TokenRequest tokenRequest;

    // 생성자
    @BeforeEach
    void setup() {
        tokenRequest = TokenRequest.builder()
                .token("test_token")
                .build();
    }

    @DisplayName("TokenRequest 클래스 / tokenRequest 테스트")
    @Test
    void tokenRequest_test() {

        assertThat(tokenRequest.token()).isEqualTo("test_token");

    }

}
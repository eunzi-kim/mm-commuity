package com.alsselssajob.auth.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogoutRequestTest {

    private LogoutRequest logoutRequest;

    // 생성자
    @BeforeEach
    void setup() {
        logoutRequest = LogoutRequest.builder()
                .userId("test_userId")
                .build();
    }

    @DisplayName("LogoutRequest 클래스 / logoutRequest 테스트")
    @Test
    void logoutRequest_test() {

        assertThat(logoutRequest.userId()).isEqualTo("test_userId");

    }

}
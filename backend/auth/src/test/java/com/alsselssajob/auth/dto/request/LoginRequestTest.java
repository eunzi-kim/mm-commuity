package com.alsselssajob.auth.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void setup() {
        loginRequest = LoginRequest.builder()
                .id("dfhh")
                .password("adfasdn")
                .build();
    }

    @DisplayName("LoginRequest 클래스 / Construct 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(loginRequest).isNotNull(),
                () -> assertThat(loginRequest).isExactlyInstanceOf(LoginRequest.class)
        );
    }


}
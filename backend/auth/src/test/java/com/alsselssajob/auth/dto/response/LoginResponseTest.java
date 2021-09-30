package com.alsselssajob.auth.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginResponseTest {
    private LoginResponse loginResponse;

    @BeforeEach
    void setUp() {
        loginResponse = LoginResponse.builder()
                .userId("test_userId")
                .nickName("test_nickName")
                .userName("test_userName")
                .build();
    }

    @DisplayName("LoginResponse 클래스 / Construct 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(loginResponse).isNotNull(),
                () -> assertThat(loginResponse).isExactlyInstanceOf(LoginResponse.class)
        );

    }

    @DisplayName("LoginResponse 클래스 / Getters 테스트")
    @Test
    void getters_test() {
        assertAll(
                () -> assertThat(loginResponse.userId()).isEqualTo("test_userId"),
                () -> assertThat(loginResponse.nickName()).isEqualTo("test_nickName"),
                () -> assertThat(loginResponse.userName()).isEqualTo("test_userName")
        );
    }


}
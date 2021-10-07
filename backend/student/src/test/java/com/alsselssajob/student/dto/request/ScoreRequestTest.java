package com.alsselssajob.student.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreRequestTest {
    private ScoreRequest scoreRequest;

    @BeforeEach
    void setup() {
        scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
    }

    @DisplayName("ScoreRequest 클래스 / Construct 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(scoreRequest).isNotNull(),
                () -> assertThat(scoreRequest).isExactlyInstanceOf(ScoreRequest.class)
        );
    }

    @DisplayName("ScoreRequest 클래스 / Getters 테스트")
    @Test
    void getters_test() {
        assertAll(
                () -> assertThat(scoreRequest.id()).isEqualTo("test_userId"),
                () -> assertThat(scoreRequest.type()).isEqualTo("type")
        );

    }
}

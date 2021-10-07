package com.alsselssajob.student.dto.response;

import com.alsselssajob.student.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreResponseTest {
    private ScoreResponse scoreResponse;
    private List<Student> ranking = new ArrayList<>();

    @BeforeEach
    void setUp() {

        scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();
    }

    @DisplayName("ScoreResponse 클래스 / Construct 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(scoreResponse).isNotNull(),
                () -> assertThat(scoreResponse).isExactlyInstanceOf(ScoreResponse.class)
        );

    }

    @DisplayName("LoginResponse 클래스 / Getters 테스트")
    @Test
    void getters_test() {
        assertAll(
                () -> assertThat(scoreResponse.list()).isEqualTo(ranking)
        );
    }
}

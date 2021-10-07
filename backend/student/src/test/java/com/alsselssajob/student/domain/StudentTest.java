package com.alsselssajob.student.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudentTest {
    private Student student;

    // token객체 자동 생성
    @BeforeEach
    void setUp() {
        student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();
    }

    @DisplayName("Student 클래스 / Construct 테스트")
    @Test
    void construct_test() {

        assertAll(
                () -> assertThat(student).isNotNull(),
                () -> assertThat(student).isExactlyInstanceOf(Student.class)

        );
    }

    @DisplayName("Token 클래스 / Getters 테스트")
    @Test
    void getters_test() {

        assertAll(
                () -> assertThat(student.userId()).isEqualTo("test_userId"),
                () -> assertThat(student.username()).isEqualTo("test_name"),
                () -> assertThat(student.postCount()).isEqualTo(1),
                () -> assertThat(student.reactingCount()).isEqualTo(2),
                () -> assertThat(student.reactedCount()).isEqualTo(3),
                () -> assertThat(student.point()).isEqualTo(6)
        );

    }

//    @DisplayName("Student 클래스 / logout 테스트")
//    @Test
//    void logout_test() {
//
//        token.switchIsActiveToFalse();
//        assertThat(token.isActive()).isFalse();
//
//    }

}

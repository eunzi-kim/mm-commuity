package com.alsselssajob.mattermostapi.post.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostRepositoryTest {

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository();
    }

    @DisplayName("PostRepository 클래스 / 생성 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(postRepository).isNotNull(),
                () -> assertThat(postRepository).isExactlyInstanceOf(PostRepository.class)
        );
    }
}
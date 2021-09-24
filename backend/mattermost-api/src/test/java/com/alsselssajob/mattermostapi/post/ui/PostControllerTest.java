package com.alsselssajob.mattermostapi.post.ui;

import net.bis5.mattermost.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostControllerTest {

    private PostController postController;

    @BeforeEach
    void setUp() {
        postController = new PostController();
    }

    @DisplayName("PostController 클래스 / 생성 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(postController).isNotNull(),
                () -> assertThat(postController).isExactlyInstanceOf(PostController.class)
        );
    }

    @DisplayName("PostController 클래스 / 로그인 테스트")
    @Test
    void login_test() {
        final User user = postController.login();

        assertThat(user.getId().length()).isEqualTo(26);
    }
}

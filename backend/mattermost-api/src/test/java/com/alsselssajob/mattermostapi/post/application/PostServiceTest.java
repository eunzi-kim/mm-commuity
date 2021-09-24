package com.alsselssajob.mattermostapi.post.application;

import net.bis5.mattermost.client4.MattermostClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostServiceTest {

    private PostService postService;
    private MattermostClient client;
    private String etag;

    @BeforeEach
    void setUp() {
        client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .ignoreUnknownProperties()
                .build();
        etag = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").getEtag();
        postService = PostService.builder()
                .client(client)
                .etag(etag)
                .build();
    }

    @DisplayName("PostService 클래스 / 생성 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(postService).isNotNull(),
                () -> assertThat(postService).isExactlyInstanceOf(PostService.class)
        );
    }
}

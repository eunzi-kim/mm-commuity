package com.alsselssajob.mattermostapi.post.application;

import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.ChannelList;
import net.bis5.mattermost.model.TeamList;
import net.bis5.mattermost.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PostServiceTest {

    private PostService postService;
    private MattermostClient client;
    private User user;

    @BeforeEach
    void setUp() {
        client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        user = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").readEntity();
        postService = PostService.builder()
                .client(client)
                .user(user)
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

    @DisplayName("PostService 클래스 / 팀 리스트 조회 테스트")
    @Test
    void get_teams_test() {
        final TeamList teams = ReflectionTestUtils.invokeMethod(postService, "getTeams");

        assertThat(teams.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("PostService 클래스 / 팀 내 채널 리스트 조회 테스트")
    @Test
    void get_channels_test() {
        final TeamList teams = ReflectionTestUtils.invokeMethod(postService, "getTeams");
        final ChannelList channels = ReflectionTestUtils.invokeMethod(postService, "getChannels", teams.get(0));

        assertThat(channels.size()).isGreaterThanOrEqualTo(0);
    }
}

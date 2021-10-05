package com.alsselssajob.mattermostapi.domain.mattermostuser.domain;

import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/application-local.properties")
class MattermostUserTest {

    @Value("${mattermost.url}")
    private String url;

    @Value("${user.id}")
    private String id;

    @Value("${user.password}")
    private String password;

    private MattermostUser mattermostUser;
    private MattermostClient client;
    private User user;

    @BeforeEach
    void setUp() {
        client = MattermostClient.builder()
                .url(url)
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        user = client.login(id, password).readEntity();
        mattermostUser = MattermostUser.builder()
                .client(client)
                .user(user)
                .build();
    }

    @DisplayName("PostService 클래스 / 생성 테스트")
    @Test
    void construct_test() {
        assertAll(
                () -> assertThat(mattermostUser).isNotNull(),
                () -> assertThat(mattermostUser).isExactlyInstanceOf(MattermostUser.class)
        );
    }

    @DisplayName("PostService 클래스 / 사용자가 속한 팀 리스트 조회 테스트")
    @Test
    void get_teams_for_user_test() {
        final TeamList teams = ReflectionTestUtils.invokeMethod(mattermostUser, "getTeamsForUser");

        assertThat(teams.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("PostService 클래스 / 팀 내 공개 채널 리스트 조회 테스트")
    @Test
    void get_public_channels_for_team_test() {
        final TeamList teams = ReflectionTestUtils.invokeMethod(mattermostUser, "getTeamsForUser");
        final ChannelList channels = ReflectionTestUtils.invokeMethod(mattermostUser, "getPublicChannelsForTeam", teams.get(0));

        assertThat(channels.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("PostService 클래스 / 채널 내 게시글 리스트 조회 테스트")
    @Test
    void get_posts_for_channel_test() {
        final TeamList teams = ReflectionTestUtils.invokeMethod(mattermostUser, "getTeamsForUser");
        final ChannelList channels = ReflectionTestUtils.invokeMethod(mattermostUser, "getPublicChannelsForTeam", teams.get(0));
        final PostList posts = ReflectionTestUtils.invokeMethod(mattermostUser, "getPostsForChannelSinceYesterday", channels.get(0));

        assertThat(posts.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("PostService 클래스 / 사용자가 속한 채널들에서 오늘 하루 올라온 게시글 리스트 조회 테스트")
    @Test
    void get_posts_for_today_test() {
        final List<Post> posts = mattermostUser.getPostsForTodayGroupByChannelGroupByTeam();

        assertThat(posts.size()).isGreaterThanOrEqualTo(0);
    }
}

package com.alsselssajob.mattermostapi.common.infra;

import com.alsselssajob.mattermostapi.domain.ssafycial.infra.SsafycialUtil;
import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.System;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@NoArgsConstructor
public class MattermostUser {

    private final static int SSAFYCIAL_CHANNEL_INDEX = 0;
    private final static int ONE_DAY = 1;
    private final static long TWO_WEEKS = 2;

    private MattermostClient client;
    private User user;

    @Builder
    public MattermostUser(final MattermostClient client, final User user) {
        this.client = client;
        this.user = user;
    }

    public MattermostClient client() {
        return client;
    }

    public User user() {
        return user;
    }

    public List<Post> getPostsForToday() {
        return getTeamsForUser().stream()
                .map(this::getPublicChannelsForTeam)
                .flatMap(List::stream)
                .map(this::getPostsForChannelSinceYesterday)
                .map(PostList::getPosts)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(this::isPostOfStudent)
                .collect(toList());
    }

    private boolean isPostOfStudent(final Post post) {
        final User user = client.getUser(post.getUserId()).readEntity();
        final String nickname = user.getNickname();

        return !(nickname.contains(MattermostUserRole.PROFESSOR.role())
                || nickname.contains(MattermostUserRole.CONSULTANT.role())
                || nickname.contains(MattermostUserRole.EDU_PRO.role()));
    }

    public Map<String, List<Map<String, List<Post>>>> getPostsForTodayGroupByChannelGroupByTeam() {
        final Map<String, List<Map<String, List<Post>>>> postsGroupByChannelGroupByTeam = new HashMap<>();

        final List<Team> teams = getTeamsForUser();
        for (Team team : teams) {
            final List<Map<String, List<Post>>> channelsContainingPosts = new ArrayList<>();
            final List<Channel> channels = getPublicChannelsForTeam(team);

            for (Channel channel : channels) {
                final Map<String, List<Post>> postsGroupByChannel = new HashMap<>();
                final Map<String, Post> posts = getPostsForChannelSinceYesterday(channel).getPosts();

                if (Objects.nonNull(posts)) {
                    postsGroupByChannel.put(channel.getDisplayName(), new ArrayList<>(posts.values()));
                    channelsContainingPosts.add(postsGroupByChannel);
                }
            }
            postsGroupByChannelGroupByTeam.put(team.getDisplayName(), channelsContainingPosts);
        }

        return postsGroupByChannelGroupByTeam;
    }

    private List<Team> getTeamsForUser() {
        return client.getTeamsForUser(user.getId()).readEntity();
    }

    private List<Channel> getPublicChannelsForTeam(final Team team) {
        return client.getPublicChannelsForTeam(team.getId()).readEntity();
    }

    private PostList getPostsForChannelSinceYesterday(final Channel channel) {
        return client.getPostsSince(channel.getId(), LocalDateTime.now()
                .minusDays(ONE_DAY)
                .atZone(ZoneId.systemDefault()))
                .readEntity();
    }

    public List<Ssafycial> getSsafycialsForLastTwoWeeks(final String pressCorpsTeamId, final String ssafycialChannelId) {
        return Stream.of(getSsafycialsForChannelSinceLastTwoWeeks(
                getSsafycialChannel(pressCorpsTeamId, ssafycialChannelId)))
                .map(PostList::getPosts)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .map(SsafycialUtil::parsePostToSsafycial)
                .flatMap(List::stream)
                .collect(toList());
    }

    private Channel getSsafycialChannel(final String pressCorpsTeamId, final String ssafycialChannelId) {
        System.out.println(pressCorpsTeamId + " " + ssafycialChannelId);
        return client.getPublicChannelsByIdsForTeam(pressCorpsTeamId, ssafycialChannelId)
                .readEntity()
                .get(SSAFYCIAL_CHANNEL_INDEX);
    }

    private PostList getSsafycialsForChannelSinceLastTwoWeeks(final Channel channel) {
        return client.getPostsSince(channel.getId(), LocalDateTime.now()
                .minusWeeks(TWO_WEEKS)
                .atZone(ZoneId.systemDefault()))
                .readEntity();
    }
}

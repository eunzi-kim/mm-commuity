package com.alsselssajob.mattermostapi.domain.mattermostuser.domain;

import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;

import java.lang.System;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;

public class MattermostUser {

    private final static String PRESS_CORPS_TEAM_ID = "jnai78zewj87dfjwxtj8qmuydr";
    private final static String SSAFYCIAL_CHANNEL_ID = "9yxif5ehwirt7eo4wyz34af67e";
    private final static int ONE_DAY = 1;
    private final static long TWO_WEEKS = 2;
    private final static int SSAFYCIAL_CHANNEL_INDEX = 0;

    private final MattermostClient client;
    private final User user;

    @Builder
    public MattermostUser(final MattermostClient client, final User user) {
        this.client = client;
        this.user = user;
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
                .collect(toList());
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

    private Channel getSsafycialChannel() {
        return client.getPublicChannelsByIdsForTeam(PRESS_CORPS_TEAM_ID, SSAFYCIAL_CHANNEL_ID)
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

package com.alsselssajob.mattermostapi.mattermostuser.domain;

import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class MattermostUser {

    private final static int ONE_DAY = 1;

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
}

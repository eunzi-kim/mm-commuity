package com.alsselssajob.mattermostapi.post.application;

import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PostService {

    private final static int ONE_DAY = 1;

    private final MattermostClient client;
    private final User user;

    @Builder
    public PostService(final MattermostClient client, final User user) {
        this.client = client;
        this.user = user;
    }

    private TeamList getTeamsForUser() {
        return client.getTeamsForUser(user.getId()).readEntity();
    }

    private ChannelList getPublicChannelsForTeam(final Team team) {
        return client.getPublicChannelsForTeam(team.getId()).readEntity();
    }

    private PostList getPostsForChannelSinceYesterday(final Channel channel) {
        return client.getPostsSince(channel.getId(), LocalDateTime.now()
                .minusDays(ONE_DAY)
                .atZone(ZoneId.systemDefault()))
                .readEntity();
    }
}

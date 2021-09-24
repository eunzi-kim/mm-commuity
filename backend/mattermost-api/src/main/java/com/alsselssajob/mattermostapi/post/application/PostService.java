package com.alsselssajob.mattermostapi.post.application;

import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.ChannelList;
import net.bis5.mattermost.model.Team;
import net.bis5.mattermost.model.TeamList;
import net.bis5.mattermost.model.User;

public class PostService {

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
}

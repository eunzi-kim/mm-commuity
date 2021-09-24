package com.alsselssajob.mattermostapi.post.application;

import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.client4.Pager;
import net.bis5.mattermost.model.TeamList;

public class PostService {

    private final MattermostClient client;
    private final String etag;

    @Builder
    public PostService(final MattermostClient client, final String etag) {
        this.client = client;
        this.etag = etag;
    }

    private TeamList getTeams() {
        return client.getAllTeams(Pager.defaultPager(), etag).readEntity();
    }
}

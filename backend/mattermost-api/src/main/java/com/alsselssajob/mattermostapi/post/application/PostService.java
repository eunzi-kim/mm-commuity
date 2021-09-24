package com.alsselssajob.mattermostapi.post.application;

import lombok.Builder;
import net.bis5.mattermost.client4.MattermostClient;

public class PostService {

    private final MattermostClient client;
    private final String etag;

    @Builder
    public PostService(final MattermostClient client, final String etag) {
        this.client = client;
        this.etag = etag;
    }
}

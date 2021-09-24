package com.alsselssajob.mattermostapi.post.ui;

import net.bis5.mattermost.client4.MattermostClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Level;

public class PostController {

    private final MattermostClient client;

    @Value("${mattermost.url}")
    private String url;

    @Value("${user.id}")
    private String id;

    @Value("${user.password}")
    private String password;

    public PostController() {
        client = MattermostClient.builder()
                .url(url)
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
    }

}

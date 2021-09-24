package com.alsselssajob.mattermostapi.post.ui;

import com.alsselssajob.mattermostapi.post.application.PostService;
import lombok.NoArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;

@Component
@NoArgsConstructor
public class PostController {

    private MattermostClient client;

    @Value("${mattermost.url}")
    private String url;

    @Value("${user.id}")
    private String id;

    @Value("${user.password}")
    private String password;

    @PostConstruct
    public void init() {
        client = MattermostClient.builder()
                .url(url)
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
    }

    private User login() {
        return client.login(id, password).readEntity();
    }

    public List<Post> getPosts() {
        final PostService postService = PostService.builder()
                .client(client)
                .user(login())
                .build();

        return postService.getPostsForToday();
    }
}

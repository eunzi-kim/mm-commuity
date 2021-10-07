package com.alsselssajob.mattermostapi.domain.post.ui;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.post.application.PostService;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Level;

@CrossOrigin(originPatterns = "*")
@EnableScheduling
@Component
@RequiredArgsConstructor
public class PostController {

    private final static String EVERY_ZERO_AM_CRON_EXPRESSION = "0 0 0 * * *";
    private final static String EVERY_MINUTE_CRON_EXPRESSION_FOR_TEST = "0 0/1 * * * *";

    private final PostService postService;
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

    @Scheduled(cron = EVERY_ZERO_AM_CRON_EXPRESSION)
    public void savePostsEveryDay() throws IOException {
        final User user = login();
        final MattermostUser mattermostUser = MattermostUser.builder()
                .client(client)
                .user(user)
                .build();

        postService.savePosts(client, mattermostUser.getPostsForTodayGroupByChannelGroupByTeam());
    }
}

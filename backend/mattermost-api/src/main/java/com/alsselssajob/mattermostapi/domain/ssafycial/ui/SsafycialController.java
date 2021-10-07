package com.alsselssajob.mattermostapi.domain.ssafycial.ui;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.ssafycial.applicaion.SsafycialService;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Level;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class SsafycialController {

    private final static String EVERY_TWO_WEEKS_AT_ONE_AM_CRON_EXPRESSION = "0 10 0 1/14 * ?";
    private final static String CRON_EXPRESSION_FOR_TEST = "0/50 * * * * *";

    private final SsafycialService ssafycialService;
    private MattermostClient client;

    @Value("${mattermost.url}")
    private String url;

    @Value("${user.id}")
    private String id;

    @Value("${user.password}")
    private String password;

    @Value("${ssafycial.channel.id}")
    private String ssafycialChannelId;

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

    @Scheduled(cron = EVERY_TWO_WEEKS_AT_ONE_AM_CRON_EXPRESSION)
    public void saveSsafycialsEveryTwoWeeks() throws IOException {
        final User user = login();
        final MattermostUser mattermostUser = MattermostUser.builder()
                .client(client)
                .user(user)
                .build();

        ssafycialService.saveSsafycials(client, mattermostUser.getSsafycialsForLastTwoWeeks(ssafycialChannelId));
    }
}

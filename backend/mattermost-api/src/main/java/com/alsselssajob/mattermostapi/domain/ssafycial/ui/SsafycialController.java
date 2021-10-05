package com.alsselssajob.mattermostapi.domain.ssafycial.ui;

import com.alsselssajob.mattermostapi.domain.mattermostuser.domain.MattermostUser;
import com.alsselssajob.mattermostapi.domain.ssafycial.applicaion.SsafycialService;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Level;

@Component
@RequiredArgsConstructor
public class SsafycialController {

    private final SsafycialService ssafycialService;
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

    public void saveSsafycialsEveryTwoWeeks() throws IOException {
        final User user = login();
        final MattermostUser mattermostUser = MattermostUser.builder()
                .client(client)
                .user(user)
                .build();

        ssafycialService.saveSsafycials(user, mattermostUser.getSsafycialsForLastTwoWeeks());
    }
}

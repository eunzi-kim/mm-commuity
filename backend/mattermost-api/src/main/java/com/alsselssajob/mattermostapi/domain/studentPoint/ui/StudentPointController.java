package com.alsselssajob.mattermostapi.domain.studentPoint.ui;

import com.alsselssajob.mattermostapi.domain.studentPoint.application.StudentPointService;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Level;

@Component
@RequiredArgsConstructor
public class StudentPointController {

    private final StudentPointService studentPointService;
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

}

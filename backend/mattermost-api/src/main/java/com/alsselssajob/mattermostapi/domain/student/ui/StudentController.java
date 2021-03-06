package com.alsselssajob.mattermostapi.domain.student.ui;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.student.application.StudentService;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Level;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class StudentController {

    private final static String EVERY_ZERO_AM_FIVE_MINUTE_CRON_EXPRESSION = "0 5 0 * * *";
    private final static String CRON_EXPRESSION_FOR_TEST = "* 0/1 * * * *";

    private final StudentService studentService;
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

    @Scheduled(cron = EVERY_ZERO_AM_FIVE_MINUTE_CRON_EXPRESSION)
    public void updateStudentPoints() {
        final MattermostUser mattermostUser = MattermostUser.builder()
                .client(client)
                .user(login())
                .build();

        studentService.updateStudentPoint(mattermostUser);
    }
}

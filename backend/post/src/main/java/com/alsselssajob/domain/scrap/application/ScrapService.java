package com.alsselssajob.domain.scrap.application;

import com.alsselssajob.domain.scrap.dto.request.RequestScrap;
import com.alsselssajob.domain.scrap.dto.response.ResponseScrap;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;

@Service
public class ScrapService {

    public List<ResponseScrap> getScrapPosts(RequestScrap requestScrap) {
        List<ResponseScrap> responseScraps = new ArrayList<>();

        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        client.setAccessToken(requestScrap.token());
        client.getFlaggedPostsForUser(requestScrap.userId());

    }
}

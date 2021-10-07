package com.alsselssajob.domain.scrap.application;

import com.alsselssajob.domain.scrap.dto.request.RequestScrap;
import com.alsselssajob.domain.scrap.dto.response.ResponseScrap;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;

@Service
public class ScrapService {


    private final static String EMPTY = "";
    private final static String DASH = "-";
    private final static String SPACE = " ";
    private final static String COLON = ":";

    public String getProfileImage(String userId) {
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();

        User user = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").readEntity();
        String encoded = "data:image/png;base64," + Base64.getEncoder().encodeToString(client.getProfileImage(userId).readEntity());
        return encoded;
    }

    private String calculateCreatedDate(final Post post) {
        final LocalDateTime createdDate = Instant.ofEpochMilli(post.getCreateAt())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new StringBuilder().append(createdDate.getYear())
                .append(DASH)
                .append(createdDate.getMonthValue())
                .append(DASH)
                .append(createdDate.getDayOfMonth())
                .append(SPACE)
                .append(createdDate.getHour())
                .append(COLON)
                .append(createdDate.getMinute())
                .append(COLON)
                .append(createdDate.getSecond())
                .toString();
    }

    public List<ResponseScrap> getScrapPosts(RequestScrap requestScrap) {
        List<ResponseScrap> responseScraps = new ArrayList<>();

        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        client.setAccessToken(requestScrap.token());

        Map<String,Post> posts = client.getFlaggedPostsForUser(requestScrap.userId()).readEntity().getPosts();
        Iterator<String> keys = posts.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            Post post = posts.get(key);
            User user = client.getUsersByIds(post.getUserId()).readEntity().get(0);

            ResponseScrap responseScrap = ResponseScrap.builder()
                    .postId(key)
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .profileImg(getProfileImage(user.getId()))
                    .message(post.getMessage())
                    .isScrapped(true)
                    .createdDate(calculateCreatedDate(post))
                    .build();
            responseScraps.add(responseScrap);

        }
        return responseScraps;
    }
}

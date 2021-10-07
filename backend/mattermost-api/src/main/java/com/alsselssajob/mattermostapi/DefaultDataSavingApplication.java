package com.alsselssajob.mattermostapi;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.post.repository.PostRepository;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;

import java.io.IOException;
import java.util.logging.Level;

public class DefaultDataSavingApplication {

    public static void main(String[] args) throws IOException {
        PostRepository postRepository = new PostRepository();
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        User user = client.login("mymysuzy0627@gmail.com", "Qwer1234!!").readEntity();
        MattermostUser mattermostUser = MattermostUser.builder()
                .client(client)
                .user(user)
                .build();
        postRepository.savePosts(client, mattermostUser.getAllPostsGroupByChannelGroupByTeam());
    }

}

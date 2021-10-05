package com.alsselssajob.mattermostapi.domain.post.application;

import com.alsselssajob.mattermostapi.domain.mattermostuser.domain.MattermostUser;
import com.alsselssajob.mattermostapi.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePosts(final User user, final Map<String, List<Map<String, List<Post>>>> teams) throws IOException {
        postRepository.savePosts(user,teams);
    }
}

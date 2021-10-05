package com.alsselssajob.mattermostapi.domain.post.application;

import com.alsselssajob.mattermostapi.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


}

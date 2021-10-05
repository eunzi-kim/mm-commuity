package com.alsselssajob.post.ui;

import com.alsselssajob.post.application.PostService;
import com.alsselssajob.post.dto.response.ResponsePost;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts/{today_date}")
    public List<ResponsePost> getPosts(@PathVariable String today_date) throws IOException {
        return postService.getPosts(today_date);
    }
}

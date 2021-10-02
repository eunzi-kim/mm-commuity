package com.alsselssajob.post.ui;

import com.alsselssajob.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/setPost")
    public void setPost() {
        postService.setPost();
    }
}

package com.alsselssajob.domain.ui;

import com.alsselssajob.domain.application.PostService;
import com.alsselssajob.domain.dto.response.ResponsePost;
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

    @GetMapping("/api/posts/redis/{today_date}")
    public void getPostsWithRedis(@PathVariable String today_date) throws IOException {
        postService.getPostsWithRedis(today_date);
    }

//    @GetMapping(
//            value = "/image"
////            produces = MediaType.IMAGE_JPEG_VALUE
//    )
//    public byte[] getProfileImage() {
//        return postService.getProfileImage();
//    }
}

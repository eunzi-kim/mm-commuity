package com.alsselssajob.domain.post.ui;

import com.alsselssajob.domain.post.application.PostService;
import com.alsselssajob.domain.post.dto.response.ResponsePost;
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
    public List<ResponsePost> getPostsWithRedis(@PathVariable String today_date) throws IOException {
        return postService.getPostsWithRedis(today_date);
    }

//    @GetMapping(
//            value = "/image/{user_id}"
////            produces = MediaType.IMAGE_JPEG_VALUE
//    )
//    public String getProfileImage(@PathVariable String user_id) {
//        return postService.getProfileImage(user_id);
//    }
}

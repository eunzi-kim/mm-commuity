package com.alsselssajob.post.application;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostService {

//    작성해놓은 redisPostTemplate 주입
    @Resource(name="redisPostTemplate")
    RedisTemplate<String, String> redisPostTemplate;

//    게시글 조회
    public void setPost() {
//        날짜 해당하는 게시물이 redis에 있는지 확인 (key로 확인)
//        redis에 있으면 가져와서 리턴
//        없으면 HBase에서 가져온 뒤, redis에 넣고 리턴
        ListOperations<String, String> stringStringListOperations = redisPostTemplate.opsForList();
        stringStringListOperations.rightPushAll("post2", "date", "post_id", "user_id");
        stringStringListOperations.rightPushAll("post2", "date2", "post_id2", "user_id2");

    }
}

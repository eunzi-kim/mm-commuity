package com.alsselssajob.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private RedisSampleService redisSampleService;

    @PostMapping(value = "/getRedisStringValue")
    public String getRedisStringValue(String key) {
        return redisSampleService.getRedisStringValue(key);
    }

    @PostMapping(value = "/setRedisStringValue")
    public String setRedisStringValue(String key, String value) {
        return redisSampleService.setRedisStringValue(key, value);
    }

}

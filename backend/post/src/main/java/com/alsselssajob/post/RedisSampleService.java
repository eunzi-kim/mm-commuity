package com.alsselssajob.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSampleService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getRedisStringValue(String key) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String result = "key: " + key + "\nvalue: " + stringValueOperations.get(key);
        return result;
    }

    public String setRedisStringValue(String key, String value) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key, value);
        String result = "key: " + key + "\nvalue: " + value;
        return result;
    }

}

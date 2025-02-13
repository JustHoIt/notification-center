package com.fc.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Repository
public class NotificationReadRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public NotificationReadRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Instant setLastReadAt(Long userId) {
        long lastReadAt = Instant.now().toEpochMilli();
        String key = userId + ":lastReadAt";
        redisTemplate.opsForValue().set(key, String.valueOf(lastReadAt));
        redisTemplate.expire(key, 90, TimeUnit.DAYS);

        return Instant.ofEpochMilli(lastReadAt);
    }

    public Instant getLastReadAt(Long userId) {
        String key = getKey(userId);
        String lastReadAtStr = redisTemplate.opsForValue().get(key);
        if(lastReadAtStr == null) {
            return null;
        }

        Long lastReadAtLong = Long.parseLong(lastReadAtStr);
        return Instant.ofEpochMilli(lastReadAtLong);
    }


    private String getKey(Long userId) {
        return userId + ":lastReadAt";
    }
}

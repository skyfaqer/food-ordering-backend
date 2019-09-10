package com.cgy.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Key: product id, value: order time + expiration period = expiration time
    public boolean lock(String key, String value) {

        // setIfAbsent() returns true if key does not exist
        // If true, set lock with value = expiration time
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        // If lock is empty or has expired
        // Avoid deadlock caused by unlocked lock
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // If multiple threads reach here, only let 1 thread to get the lock, i.e. the first thread that executes the getAndSet()
            // The other locks will not get the lock because oldValue has been updated by the first thread (oldValue != currentValue)
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            // Make sure the unlocking thread is the thread that has the lock
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[Redis distributed lock]: unlocking error, {}", e.getMessage());
        }
    }
}

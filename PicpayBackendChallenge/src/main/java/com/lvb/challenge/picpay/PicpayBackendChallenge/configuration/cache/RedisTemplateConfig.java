package com.lvb.challenge.picpay.PicpayBackendChallenge.configuration.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisTemplateConfig {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void saveWithExpire(final Object key, final Object value, final Duration duration) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, duration);
    }

    public void save(final Object key, final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object find(final Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean remove(final Object key) {
        return redisTemplate.delete(key);
    }

    public Boolean contain(final Object key) {
        return redisTemplate.hasKey(key);
    }


}

package com.siwen.seckilling.service.impl;

import com.siwen.seckilling.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author siwen
 * @description redis 服务实现类
 * @date 2021/05/12 21:34
 **/
@Resource(name = "stringRedisService")
public class StringRedisServiceImpl implements RedisService<String, Object> {

    @Resource
    private RedisTemplate<String, Object> stringRedisTemplate;

    @Override
    public boolean exists(String prefix, String key) {
        return stringRedisTemplate.hasKey(prefix + key);
    }

}

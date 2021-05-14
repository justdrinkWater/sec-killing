package com.siwen.seckilling.service.impl;

import com.alibaba.fastjson.JSON;
import com.siwen.seckilling.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author siwen
 * @description redis 服务实现类
 * @date 2021/05/12 21:34
 **/
@Service("stringRedisService")
public class StringRedisServiceImpl implements RedisService<String, Object> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JedisPool jedisPool;

    @Override
    public boolean set(String key, Object value) {
        return set(key, value, 0);
    }

    @Override
    public Long hSet(String mapKey, String key, Object value) {
        byte[] str = serialize(value);
        if (str == null || str.length <= 0) {
            return 0L;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(mapKey.getBytes(), key.getBytes(), str);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        byte[] str = serialize(value);
        if (str == null || str.length <= 0) {
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (expire > 0) {
                jedis.setex(key.getBytes(), expire, str);
            } else {
                jedis.set(key.getBytes(), str);
            }
        } finally {
            returnToPool(jedis);
        }
        return true;
    }


    @Override
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.exists(key.getBytes());
        } finally {
            returnToPool(jedis);
        }
        return true;
    }

    @Override
    public boolean hexists(String mapKey, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(mapKey, key);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public String hGet(String mapKey, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(mapKey, key);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public Long hIncr(String mapKey, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hincrBy(mapKey, key, 1L);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public long hDecr(String mapKey, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hincrBy(mapKey, key, -1L);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean hDel(String mapKey, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(mapKey, key);
        } finally {
            returnToPool(jedis);
        }
        return true;
    }

    /**
     * @author siwen
     * @date 2021/5/13 09:45
     * @description 对象序列化方法
     */
    private byte[] serialize(Object object) {
        return JSON.toJSONString(object).getBytes();
    }

    /**
     * @author siwen
     * @date 2021/5/13 10:22
     * @description 将redis连接关闭
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}

package com.siwen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author siwen
 * @description redis 配置
 * @date 2021/05/12 21:44
 **/
@Configuration
public class JedisConfiguration {

    @Bean
    public JedisPool jedisPool(com.siwen.common.config.JedisPoolConfig redisConfig) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getMaxActive());
        poolConfig.setMaxWaitMillis(redisConfig.getMaxWait() * 1000L);
        poolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
        poolConfig.setTestOnReturn(redisConfig.isTestOnReturn());
        return new JedisPool(poolConfig, redisConfig.getIp(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword());
    }
}

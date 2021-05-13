package com.siwen.seckilling.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author siwen
 * @Date 2021/5/13 9:11
 * @Description redis 配置类
 **/
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "redis")
public class JedisPoolConfig {
    private String ip;
    private int port;
    private String password;
    private int timeout;//秒
    private int maxActive;//最大存活数
    private int maxIdle;
    private int maxWait;//秒
    private boolean testOnBorrow;
    private boolean testOnReturn;


}

package com.siwen.goods.config;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.siwen.common.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @Author sunwei@finchina.com
 * @Date 2021/5/18 15:45
 * @Description
 **/

@Configuration
public class GoodsConfiguration {

    @Value("${nacos.config.serverAddr}")
    private String serverAddr;

    @Value("${nacos.config.dataId}")
    private String dataId;

    @Value("${nacos.config.group}")
    private String group;

    @Value("${redis.stockKey}")
    private String stockKey;

    @Resource
    private RedisService<String, Object> stringRedisService;

    @PostConstruct
    public void listener() {
        try {
            ConfigService configService = ConfigFactory.createConfigService(serverAddr);
            //注册监听,
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    //从redis中获取配置，如果不存在，或者不一致，则更新
                    String stock = stringRedisService.get(stockKey);
                    System.out.println("recieve:" + configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (Exception e) {

        }

    }
}

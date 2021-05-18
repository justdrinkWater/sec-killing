package com.siwen.seckilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author siwen
 * @description 服务启动类
 * @date 2021/05/12 21:47
 **/
@SpringBootApplication(scanBasePackages = "com.siwen")
@EnableDiscoveryClient
public class PreOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreOrderApplication.class, args);
    }
}

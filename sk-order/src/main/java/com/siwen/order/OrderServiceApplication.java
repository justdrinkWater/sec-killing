package com.siwen.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author siwen
 * @Date 2021/5/17 15:09
 * @Description 启动类
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.siwen.common.api")
@MapperScan("com.siwen.order.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

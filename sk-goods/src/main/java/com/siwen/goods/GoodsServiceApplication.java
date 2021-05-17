package com.siwen.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author siwen
 * @Date 2021/5/17 9:36
 * @Description
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.siwen.goods.mapper")
@EnableDiscoveryClient
public class GoodsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class, args);
    }
}

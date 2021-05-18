package com.siwen.order.configuration;

import com.siwen.common.constant.MQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author sunwei@finchina.com
 * @Date 2021/5/18 9:29
 * @Description
 **/
@Configuration
public class MQConfiguration {

    @Bean
    public Queue preOrderQueue() {
        return new Queue(MQConstant.PRE_ORDER, true);
    }
}

package com.siwen.order.impl;

import com.siwen.domain.Order;
import com.siwen.domain.constant.MQConstant;
import com.siwen.order.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author siwen
 * @Date 2021/5/14 17:23
 * @Description 订单处理接口
 **/
@Component
public class OrderServiceImpl implements OrderService {

    @RabbitListener(queues = MQConstant.PRE_ORDER)
    public void receive(String message) {

    }

    @Override
    public Order createOrder() {
        return null;
    }
}

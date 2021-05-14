package com.siwen.seckilling.mq;

import com.siwen.domain.constant.MQConstant;
import com.siwen.seckilling.bean.PreOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/14 16:26
 * @Description rabbit 消息发送服务
 **/
@Service
public class RabbitMQSender implements MQSender {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(PreOrder preOrder) {
        rabbitTemplate.convertAndSend(MQConstant.PRE_ORDER, preOrder);
    }
}

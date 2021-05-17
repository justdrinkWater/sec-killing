package com.siwen.seckilling.mq;

import com.siwen.common.constant.MQConstant;
import com.siwen.common.bean.PreOrder;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author siwen
 * @Date 2021/5/14 16:26
 * @Description rabbit 消息发送服务
 **/
@Service
public class RabbitMQSender implements MQSender {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(PreOrder preOrder) {
        amqpTemplate.convertAndSend(MQConstant.PRE_ORDER, preOrder);
    }
}

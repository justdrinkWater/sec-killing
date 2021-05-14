package com.siwen.seckilling.mq;

import com.siwen.seckilling.bean.PreOrder;

/**
 * @Author siwen
 * @Date 2021/5/14 16:24
 * @Description 消息发送接口
 **/
public interface MQSender {

    void send(PreOrder preOrder);
}

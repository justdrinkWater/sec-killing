package com.siwen.order.mq;

import com.alibaba.fastjson.JSON;
import com.siwen.common.api.GoodsServiceApi;
import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import com.siwen.common.bean.PreOrder;
import com.siwen.common.constant.MQConstant;
import com.siwen.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author sunwei@finchina.com
 * @Date 2021/5/17 10:27
 * @Description 消息接收器
 **/
@Component
public class MQReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private GoodsServiceApi goodsServiceApi;

    @Resource
    private OrderService orderService;

    @RabbitListener(queues = MQConstant.PRE_ORDER)
    public void receive(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        PreOrder preOrder = JSON.parseObject(message, PreOrder.class);
        Long userId = preOrder.getUserId();
        String goodsId = preOrder.getGoodsId();
        //获取商品数据
        Result<GoodsVo> result = goodsServiceApi.getGoodsVoByGoodsId(goodsId);

        if (result.isSuccess()) {
            GoodsVo goods = result.getData();
            //商品是否存在
            if (Objects.isNull(goods)) {
                logger.error(goodsId + "商品不存在");
                return;
            }
            //库存不够了
            int stock = goods.getSecKillingStockCount();
            if (stock <= 0) {
                return;
            }
            //减库存 下订单 写入秒杀订单
            orderService.createOrder(userId, goods);
        }
    }
}

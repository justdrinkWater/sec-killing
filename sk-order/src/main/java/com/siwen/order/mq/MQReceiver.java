package com.siwen.order.mq;

import com.siwen.common.api.GoodsServiceApi;
import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import com.siwen.common.bean.PreOrder;
import com.siwen.order.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author sunwei@finchina.com
 * @Date 2021/5/17 10:27
 * @Description 消息接收器
 **/
@Component
public class MQReceiver {
    @Resource
    private GoodsServiceApi goodsServiceApi;

    @Resource
    private OrderService orderService;

//    @RabbitListener(queues = MQConstant.PRE_ORDER)
    public void receive(PreOrder preOrder) {
        Long userId = preOrder.getUserId();
        String goodsId = preOrder.getGoodsId();

        Result<GoodsVo> result = goodsServiceApi.getGoodsVoByGoodsId(goodsId);

        if (result.isSuccess()) {
            GoodsVo goods = result.getData();
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

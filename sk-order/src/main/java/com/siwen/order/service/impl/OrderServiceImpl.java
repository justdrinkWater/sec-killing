package com.siwen.order.service.impl;

import com.siwen.common.api.GoodsServiceApi;
import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import com.siwen.common.domain.Order;
import com.siwen.common.domain.SecKillingOrder;
import com.siwen.order.mapper.OrderMapper;
import com.siwen.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author siwen
 * @Date 2021/5/14 17:23
 * @Description 订单处理接口
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private GoodsServiceApi goodsServiceApi;

    @Resource
    private OrderMapper orderMapper;


    @Transactional
    @Override
    public Order createOrder(Long userId, GoodsVo goods) {
        Result<Boolean> result = goodsServiceApi.reduceStock(goods.getId().toString());
        if (result.isSuccess() && Boolean.TRUE.equals(result.getData())) {
            Order order = new Order();
            order.setCreateDate(new Date());
            order.setDeliveryAddrId(0L);
            order.setGoodsCount(1);
            order.setGoodsId(goods.getId());
            order.setGoodsName(goods.getGoodsName());
            order.setGoodsPrice(goods.getSecKillingPrice());
            order.setOrderChannel(1);
            order.setStatus(0);
            order.setUserId(userId);
            orderMapper.insert(order);
            SecKillingOrder secKillingOrder = new SecKillingOrder();
            secKillingOrder.setGoodsId(goods.getId());
            secKillingOrder.setOrderId(order.getId());
            secKillingOrder.setUserId(userId);
            orderMapper.insertSecKillingOrder(secKillingOrder);
            return order;
        }
        return null;
    }
}

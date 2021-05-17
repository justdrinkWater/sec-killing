package com.siwen.order.service;


import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.domain.Order;

/**
 * @Author siwen
 * @Date 2021/5/14 17:23
 * @Description
 **/
public interface OrderService {

    /**
     * @author siwen
     * @date 2021/5/17 09:27
     * @description 创建订单
     */
    Order createOrder(Long userId, GoodsVo goodsVo);
}

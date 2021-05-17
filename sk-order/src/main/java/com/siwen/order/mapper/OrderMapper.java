package com.siwen.order.mapper;

import com.siwen.common.domain.Order;
import com.siwen.common.domain.SecKillingOrder;

/**
 * @Author siwen
 * @Date 2021/5/17 10:12
 * @Description 订单mapper
 **/
public interface OrderMapper {
    /**
     * @author siwen
     * @date 2021/5/17 10:24
     * @description 保存订单数据
     */
    long insert(Order orderInfo);

    /**
     * @author siwen
     * @date 2021/5/17 10:24
     * @description 保存秒杀订单
     */
    long insertSecKillingOrder(SecKillingOrder secKillingOrder);
}

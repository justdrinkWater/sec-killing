package com.siwen.common.domain;

import lombok.Data;

/**
 * @Author siwen
 * @Date 2021/5/17 10:07
 * @Description 秒杀订单
 **/
@Data
public class SecKillingOrder {
    private Long id;
    private Long userId;//用户ID
    private Long orderId;//订单ID
    private Long goodsId;//商品ID
}

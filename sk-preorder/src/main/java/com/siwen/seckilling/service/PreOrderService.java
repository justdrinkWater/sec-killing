package com.siwen.seckilling.service;

import com.siwen.seckilling.bean.PreOrder;

/**
 * @Author siwen
 * @Date 2021/5/12 17:09
 * @Description 该类的作用为预下单服务，包括检查是否已购买，预购买，取消预购买，
 **/
public interface PreOrderService {

    /**
     * @author siwen
     * @date 2021/5/12 17:22
     * @description 检查该用户是否已秒杀到商品
     */
    PreOrder getOrderByUserIdGoodsId(Long userId, String goodsId);

    /**
     * @return boolean 预下单是否成功
     * @author siwen
     * @date 2021/5/12 17:24
     * @description 预下单，表示秒杀到了
     */
    boolean preOrder(Long userId, String goodsId);

    /**
     * @return boolean 取消是否成功
     * @author siwen
     * @date 2021/5/12 17:25
     * @description 取消订单，抢到了，但是不购买
     */
    boolean cancelOrder(Long userId, String goodsId);
}

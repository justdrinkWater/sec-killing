package com.siwen.common.constant;

/**
 * @author siwen
 * @description redis 使用到的常量
 * @date 2021/05/12 22:23
 **/
public class RedisConstant {

    private RedisConstant() {
    }

    /**
     * 商品秒杀完标志位
     **/
    public static final String GOODS_SALE_OVER = "good_sale_over";

    /**
     * 用户秒杀商品前缀
     */
    public static final String PREFIX_GOODS_SECKILLING = "goods_seckilling_";

    /**
     * 商品库存hash key
     */
    public static final String GOODS_STOCK = "goods_stock";
}

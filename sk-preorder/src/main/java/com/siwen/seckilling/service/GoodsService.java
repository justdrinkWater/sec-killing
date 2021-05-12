package com.siwen.seckilling.service;

/**
 * @Author sunwei@finchina.com
 * @Date 2021/5/12 17:30
 * @Description 商品服务，包括校验是否已秒杀完毕，设置秒杀完毕
 **/
public interface GoodsService {

    /**
     * @return 是否已秒杀完毕
     * @author siwen
     * @date 2021/5/12 17:32
     * @description 校验是否已秒杀完毕
     */
    boolean checkSaleOver(String goodsId);

    /**
     * @return 设置秒杀完毕成功
     * @author siwen
     * @date 2021/5/12 17:33
     * @description 设置秒杀完毕成功
     */
    boolean setSaleOver(String goodsId);
}

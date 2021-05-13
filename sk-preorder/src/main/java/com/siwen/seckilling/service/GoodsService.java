package com.siwen.seckilling.service;

/**
 * @Author siwen
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

    /**
     * @author siwen
     * @date 2021/5/13 15:51
     * @description 取消秒杀结束
     */
    boolean cancelSaleOver(String goodsId);

    /**
     * @author siwen
     * @date 2021/5/13 16:55
     * @description 获取库存
     */
    int getStock(String goodsId);

    /**
     * @author siwen
     * @date 2021/5/13 16:55
     * @description 减库存
     */
    long decrStock(String goodsId);

    /**
     * @author siwen
     * @date 2021/5/13 17:04
     * @description 设置商品库存
     */
    boolean setStock(String goodsId, int stock);
}

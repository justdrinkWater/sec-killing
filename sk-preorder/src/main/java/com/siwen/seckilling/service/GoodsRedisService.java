package com.siwen.seckilling.service;

import com.siwen.common.bean.PreOrder;

/**
 * @Author siwen
 * @Date 2021/5/12 17:30
 * @Description 商品服务，包括校验是否已秒杀完毕，设置秒杀完毕
 **/
public interface GoodsRedisService {

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
    Integer getStock(String goodsId);

    /**
     * @author siwen
     * @date 2021/5/13 16:55
     * @description 减库存
     */
    String decrStockAndSavePreOrder(String goodsId, String userId, PreOrder preOrder);

}

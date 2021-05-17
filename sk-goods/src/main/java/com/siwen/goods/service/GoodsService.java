package com.siwen.goods.service;

import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.bean.PreOrder;

/**
 * @Author siwen
 * @Date 2021/5/17 9:34
 * @Description 商品服务
 **/
public interface GoodsService {
    /**
     * @author siwen
     * @date 2021/5/17 13:31
     * @description 根据商品id获取秒杀商品信息
     */
    GoodsVo getGoodsVoByGoodsId(String goodsId);


    boolean reduceStock(String goodsId);

}

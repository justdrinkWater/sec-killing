package com.siwen.goods.mapper;

import com.siwen.common.api.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author siwen
 * @Date 2021/5/17 13:15
 * @Description
 **/
public interface GoodsMapper {

    /**
     * @author siwen
     * @date 2021/5/17 17:19
     * @description 根据商品id获取商品信息
     */
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") String goodsId);

    /**
     * @author siwen
     * @date 2021/5/17 17:19
     * @description 减库存
     */
    int reduceStock(@Param("goodsId") String goodsId);
}

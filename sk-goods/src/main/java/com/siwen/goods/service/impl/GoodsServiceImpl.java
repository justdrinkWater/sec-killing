package com.siwen.goods.service.impl;

import com.siwen.common.api.vo.GoodsVo;
import com.siwen.goods.mapper.GoodsMapper;
import com.siwen.goods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/17 9:35
 * @Description 商品服务实现类
 **/
@Service
public class GoodsServiceImpl implements GoodsService {


    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public GoodsVo getGoodsVoByGoodsId(String goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(String goodsId) {
        return goodsMapper.reduceStock(goodsId) == 1;
    }


}

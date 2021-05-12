package com.siwen.seckilling.service.impl;

import com.siwen.domain.Goods;
import com.siwen.seckilling.service.GoodsService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/12 17:26
 * @Description 预下单服务
 **/
@Service
public class PreOrderServiceImpl implements PreOrderService {

    @Resource
    private RedisService stringRedisService;

    @Resource
    private GoodsService goodsService;

    @Override
    public Goods getOrderByUserIdGoodsId(Long userId, String goodsId) {
        return null;
    }

    @Override
    public boolean preOrder(Long userId, String goodsId) {
        return false;
    }

    @Override
    public boolean cancelOrder(Long userId, String goodsId) {
        return false;
    }
}

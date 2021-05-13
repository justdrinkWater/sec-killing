package com.siwen.seckilling.service.impl;

import com.alibaba.fastjson.JSON;
import com.siwen.seckilling.bean.PreOrder;
import com.siwen.seckilling.constant.RedisConstant;
import com.siwen.seckilling.service.GoodsService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/12 17:26
 * @Description 预下单服务
 **/
@Service
public class PreOrderServiceImpl implements PreOrderService {

    @Resource
    private RedisService<String, Object> stringRedisService;

    @Resource
    private GoodsService goodsService;

    @Override
    public PreOrder getOrderByUserIdGoodsId(Long userId, String goodsId) {
        String orderStr = stringRedisService.hGet(RedisConstant.PREFIX_GOODS_SECKILLING + goodsId, userId.toString());
        if (StringUtils.isEmpty(orderStr)) {
            return null;
        }
        return JSON.parseObject(orderStr, PreOrder.class);
    }

    @Transactional
    @Override
    public boolean preOrder(Long userId, String goodsId) {
        //扣库存
        long stock = stringRedisService.hDecr(RedisConstant.GOODS_STOCK, goodsId);
        //扣减之后的库存  < 0，表示库存扣减失败
        if (stock < 0) {
            return false;
        }

        //判断库存是否为0，更新标志位已售完
        if (stock == 0) {
            goodsService.setSaleOver(goodsId);
        }

        //生成预订单
        PreOrder preOrder = new PreOrder(userId, goodsId);
        stringRedisService.hSet(RedisConstant.PREFIX_GOODS_SECKILLING + goodsId, userId.toString(), preOrder);

        return true;
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long userId, String goodsId) {
        //删除用户的预订单
        stringRedisService.hDel(RedisConstant.PREFIX_GOODS_SECKILLING + goodsId, userId.toString());

        //加库存
        stringRedisService.hIncr(RedisConstant.GOODS_STOCK, goodsId);

        //删除已售完标志位
        goodsService.cancelSaleOver(goodsId);
        return true;
    }
}

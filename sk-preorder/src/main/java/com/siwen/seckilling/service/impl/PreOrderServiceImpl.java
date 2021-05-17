package com.siwen.seckilling.service.impl;

import com.alibaba.fastjson.JSON;
import com.siwen.common.bean.PreOrder;
import com.siwen.common.constant.RedisConstant;
import com.siwen.seckilling.service.GoodsRedisService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisService<String, Object> stringRedisService;

    @Resource
    private GoodsRedisService goodsRedisService;

    @Override
    public PreOrder getOrderByUserIdGoodsId(Long userId, String goodsId) {
        String orderStr = stringRedisService.hGet(RedisConstant.PREFIX_GOODS_SECKILLING + goodsId, userId.toString());
        if (StringUtils.isEmpty(orderStr)) {
            return null;
        }
        return JSON.parseObject(orderStr, PreOrder.class);
    }

    /**
     * 0：表示已经抢光了
     * 1: 表示抢成功了
     * 2：表示已经抢过了
     */
    @Transactional
    @Override
    public int preOrder(Long userId, String goodsId) {
        //判断库存是否足够
        int preStock = goodsRedisService.getStock(goodsId);
        if (preStock <= 0) {
            return 0;
        }

        //先生成预订单，分布式锁，表示用户已经购买过了，否则并发情况下会产生重复购买的情况
        PreOrder preOrder = new PreOrder(userId, goodsId);
        String result = goodsRedisService.decrStockAndSavePreOrder(goodsId, userId.toString(), preOrder);
        return Integer.parseInt(result);
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long userId, String goodsId) {
        //删除用户的预订单
        stringRedisService.hDel(RedisConstant.PREFIX_GOODS_SECKILLING + goodsId, userId.toString());

        //加库存
//        stringRedisService.incr(RedisConstant.PREFIX_GOODS_STOCK+ goodsId);

        //删除已售完标志位
        goodsRedisService.cancelSaleOver(goodsId);
        return true;
    }
}

package com.siwen.seckilling.service.impl;

import com.siwen.seckilling.constant.RedisConstant;
import com.siwen.seckilling.service.GoodsService;
import com.siwen.seckilling.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/13 14:23
 * @Description
 **/

/**
 * @author siwen
 * @date 2021/5/13 14:23
 * @description 商品服务，包括校验是否已卖完，设置已卖完
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private RedisService<String, Object> stringRedisService;

    @Override
    public boolean checkSaleOver(String goodsId) {
        return stringRedisService.hexists(RedisConstant.GOODS_SALE_OVER, goodsId);
    }

    @Override
    public boolean setSaleOver(String goodsId) {
        return stringRedisService.hSet(RedisConstant.GOODS_SALE_OVER, goodsId, 1);
    }

    @Override
    public boolean cancelSaleOver(String goodsId) {
        return stringRedisService.hDel(RedisConstant.GOODS_SALE_OVER, goodsId);
    }

    @Override
    public int getStock(String goodsId) {
        String stock = stringRedisService.hGet(RedisConstant.GOODS_STOCK, goodsId);
        if (!StringUtils.isEmpty(stock)) {
            return Integer.parseInt(stock);
        }
        return 0;
    }

    @Override
    public long decrStock(String goodsId) {
        return stringRedisService.hDecr(RedisConstant.GOODS_STOCK, goodsId);
    }

    @Override
    public boolean setStock(String goodsId, int stock) {
        return stringRedisService.hSet(RedisConstant.GOODS_STOCK, goodsId, stock);
    }
}

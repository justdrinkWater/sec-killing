package com.siwen.seckilling.service.impl;

import com.alibaba.fastjson.JSON;
import com.siwen.common.bean.PreOrder;
import com.siwen.common.constant.RedisConstant;
import com.siwen.common.service.RedisService;
import com.siwen.seckilling.service.GoodsRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GoodsRedisServiceImpl implements GoodsRedisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 输入参数
     * 1: goodsId
     * 2: userId
     * 3: goods_seckilling_key 用户预订单key
     * 4: goods_stock_hash_key 库存hash key
     * 5：stock_over_key 售完的key
     * 6: stock_over_value 售完标志位
     * 7: pre_order 预订单
     * 返回结果：
     * 0：表示已经抢光了
     * 1: 表示抢成功了
     * 2：表示已经抢过了
     */
    private static final String DECR_SCRIPT =
            "local goods_id=KEYS[1];\r\n" +
                    "local user_id=KEYS[2];\r\n" +
                    "local goods_seckilling_key=KEYS[3];\r\n" +
                    "local stock_hash_key=KEYS[4];\r\n" +
                    "local stock_over_key=KEYS[5];\r\n" +
                    "local stock_over_value=KEYS[6];\r\n" +
                    "local pre_order=KEYS[7];\r\n" +
                    "local userExists=redis.call(\"hexists\", goods_seckilling_key, user_id);\r\n" +
                    "if tonumber(userExists)==1 then \r\n" +
                    "   return 2;\r\n" +
                    "end\r\n" +
                    "local num = redis.call(\"hget\" , stock_hash_key, goods_id);\r\n" +
                    "if tonumber(num)<=0 then \r\n" +
                    "   redis.call(\"hset\", stock_over_key, goods_id, stock_over_value);\r\n" +
                    "   return 0;\r\n" +
                    "else \r\n" +
                    "   redis.call(\"hincrBy\", stock_hash_key, goods_id, -1);\r\n" +
                    "   redis.call(\"hset\", goods_seckilling_key, user_id, pre_order);\r\n" +
                    "end\r\n" +
                    "return 1";

    @Resource
    private RedisService<String, Object> stringRedisService;

    @Override
    public boolean cancelSaleOver(String goodsId) {
        return stringRedisService.hDel(RedisConstant.GOODS_SALE_OVER, goodsId);
    }

    @Override
    public Integer getStock(String goodsId) {
        String stock = stringRedisService.hGet(RedisConstant.GOODS_STOCK, goodsId);
        if (!StringUtils.isEmpty(stock)) {
            return Integer.parseInt(stock);
        }
        return null;
    }

    /**
     * @author siwen
     * @date 2021/5/14 15:05
     * @description 使用lua脚本保证原子性，业务逻辑为：
     * 判断是否已经购买过了：
     * 是
     * 返回2
     * 否
     * 判断库存是否足够
     * 是
     * 减库存，保存订单，返回1
     * 否
     * 返回0
     */
    @Override
    public String decrStockAndSavePreOrder(String goodsId, String userId, PreOrder preOrder) {
        return stringRedisService.execLua(DECR_SCRIPT, goodsId, userId, RedisConstant.PREFIX_GOODS_SECKILLING + goodsId,
                RedisConstant.GOODS_STOCK, RedisConstant.GOODS_SALE_OVER, "1", JSON.toJSONString(preOrder)).toString();
    }

}

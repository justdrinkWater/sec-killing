package com.siwen.seckilling.service.impl;

import com.siwen.common.api.vo.Result;
import com.siwen.common.constant.ResultStatus;
import com.siwen.common.domain.User;
import com.siwen.seckilling.mq.RabbitMQSender;
import com.siwen.seckilling.service.GoodsRedisService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.SecKillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/12 16:35
 * @Description 秒杀服务
 **/
@Service
public class SecKillingServiceImpl implements SecKillingService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PreOrderService preOrderService;

    @Resource
    private RabbitMQSender sender;

    @Resource
    private GoodsRedisService goodsRedisService;


    @Override
    public Result<Boolean> doSecKilling(User user, String goodsId) {
        Result<Boolean> result;
        //判断库存是否存在，是否足够
        Integer preStock = goodsRedisService.getStock(goodsId);
        if (null == preStock) {
            return Result.build(ResultStatus.NOT_SEC_KILLING_GOODS);
        }
        if (preStock <= 0) {
            logger.info(goodsId + "秒杀结束");
            return Result.build(ResultStatus.SALE_OVER);
        }

        int flag = preOrderService.preOrder(user.getId(), goodsId);
        //      0：表示已经抢光了
        //      1: 表示抢成功了
        //      2：表示已经抢过了
        if (0 == flag) {
            logger.info(goodsId + "秒杀结束");
            result = Result.build(ResultStatus.SALE_OVER);
        } else if (1 == flag) {
            logger.info(user.getId() + "秒杀成功");
            sender.send(preOrderService.getOrderByUserIdGoodsId(user.getId(), goodsId));
            result = Result.buildSuccess();
            result.setData(true);
        } else if (2 == flag) {
            result = Result.build(ResultStatus.REPEAT_SEC_KILLING);
        } else {
            result = Result.build(ResultStatus.EXCEPTION);
        }

        return result;
    }


}

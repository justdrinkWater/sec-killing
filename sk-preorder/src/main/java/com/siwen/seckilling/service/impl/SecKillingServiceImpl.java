package com.siwen.seckilling.service.impl;

import com.siwen.domain.User;
import com.siwen.seckilling.bean.PreOrder;
import com.siwen.seckilling.constant.ResultStatus;
import com.siwen.seckilling.service.GoodsService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.SecKillingService;
import com.siwen.seckilling.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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
    private GoodsService goodsService;


    @Override
    public Result<Boolean> doSecKilling(User user, String goodsId) {
        Result<Boolean> result;
        int flag = preOrderService.preOrder(user.getId(), goodsId);
        //      0：表示已经抢光了
        //      1: 表示抢成功了
        //      2：表示已经抢过了
        if (0 == flag) {
            logger.info(goodsId + " 抢光了");
            result = Result.build(ResultStatus.SALE_OVER);
        } else if (1 == flag) {
            logger.info(user.getId() + " 抢到了");
            result = Result.buildSuccess();
            result.setData(true);
        } else if (2 == flag) {
            logger.info(user.getId() + " 重复抢了");
            result = Result.build(ResultStatus.REPEAT_SEC_KILLING);
        } else {
            result = Result.build(ResultStatus.EXCEPTION);
        }

        return result;
    }


}

package com.siwen.seckilling.service.impl;

import com.siwen.domain.Goods;
import com.siwen.domain.User;
import com.siwen.seckilling.constant.ResultStatus;
import com.siwen.seckilling.service.GoodsService;
import com.siwen.seckilling.service.PreOrderService;
import com.siwen.seckilling.service.SecKillingService;
import com.siwen.seckilling.vo.Result;
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

    @Resource
    private PreOrderService preOrderService;

    @Resource
    private GoodsService goodsService;


    @Override
    public Result<Boolean> doSecKilling(User user, String goodsId) {
        // 校验是否已经秒杀到了
        Goods goods = preOrderService.getOrderByUserIdGoodsId(user.getId(), goodsId);
        if (Objects.nonNull(goods)) {
            return Result.build(ResultStatus.REPEAT_SEC_KILLING);
        }

        //校验是否已经卖完
        boolean saleOverFlag = goodsService.checkSaleOver(goodsId);
        if (saleOverFlag) {
            return Result.build(ResultStatus.SALE_OVER);
        }

        //预减库存
        Result<Boolean> result = Result.buildSuccess();
        boolean flag = preOrderService.preOrder(user.getId(), goodsId);
        result.setData(flag);

        return result;
    }
}

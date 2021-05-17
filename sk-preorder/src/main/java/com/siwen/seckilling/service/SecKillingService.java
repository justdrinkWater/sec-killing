package com.siwen.seckilling.service;

import com.siwen.common.domain.User;
import com.siwen.common.api.vo.Result;

/**
 * @Author siwen
 * @Date 2021/5/12 16:34
 * @Description 秒杀服务
 **/
public interface SecKillingService {

    /**
     * @param user    用户
     * @param goodsId 商品id
     * @return boolean 是否秒杀成功
     * @Author siwen
     * @Description 秒杀服务
     * @Date 16:37
     **/
    Result<Boolean> doSecKilling(User user, String goodsId);
}

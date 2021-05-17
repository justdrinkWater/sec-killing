package com.siwen.seckilling.controller;

import com.siwen.common.api.vo.Result;
import com.siwen.common.constant.ResultStatus;
import com.siwen.common.domain.User;
import com.siwen.seckilling.service.GoodsRedisService;
import com.siwen.seckilling.service.SecKillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author siwen
 * @Date 2021/5/12 15:45
 * @Description 该类的作用为秒杀的入口
 **/

@Controller
public class SecKillingController {

    private static Logger logger = LoggerFactory.getLogger(SecKillingController.class);

    @Resource
    private SecKillingService secKillingService;

    @Resource
    private GoodsRedisService goodsRedisService;

    /**
     * @param user    用户信息
     * @param goodsId 商品id
     * @Author siwen
     * @Description 秒杀的入口类
     * @Date 16:20
     **/
    @PostMapping(value = "/sec_killing")
    @ResponseBody
    public Result<Boolean> secKilling(User user, @RequestParam("goodsId") String goodsId) {
        Result<Boolean> result = Result.buildSuccess();
        //校验参数是否正确
        if (Objects.isNull(user) || StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(goodsId)) {
            result.setResultStatus(ResultStatus.PARAM_EXCEPTION);
            return result;
        }

        //调用秒杀服务
        return secKillingService.doSecKilling(user, goodsId);
    }

}

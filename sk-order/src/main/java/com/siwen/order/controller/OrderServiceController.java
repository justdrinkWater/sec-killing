package com.siwen.order.controller;

import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import com.siwen.common.domain.Order;
import com.siwen.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author siwen
 * @date 2021/5/17 15:14
 * @description 订单服务接口
 */
@RestController
@RequestMapping(value = "order")
public class OrderServiceController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "createOrder")
    public Result<Order> createOrder(@RequestParam("userId") String userId) {
        Result<Order> result = Result.buildSuccess();
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setId(1L);
        Order order = orderService.createOrder(Long.valueOf(userId), goodsVo);
        result.setData(order);
        return result;
    }
}

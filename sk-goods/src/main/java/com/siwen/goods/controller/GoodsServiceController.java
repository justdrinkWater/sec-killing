package com.siwen.goods.controller;

import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import com.siwen.goods.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author siwen
 * @Date 2021/5/17 13:22
 * @Description 商品服务接口
 **/
@RestController
@RequestMapping(value = "goods")
public class GoodsServiceController {

    @Resource
    private GoodsService goodsService;

    @GetMapping(value = "getGoodsVoByGoodsId")
    public Result<GoodsVo> getGoodsVoByGoodsId(@RequestParam("goodsId") String goodsId) {
        Result<GoodsVo> result = Result.buildSuccess();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        result.setData(goodsVo);
        return result;
    }


    @GetMapping("/reduceStock")
    public Result<Boolean> reduceStock(@RequestParam("goodsId") String goodsId) {
        Result<Boolean> result = Result.buildSuccess();
        boolean flag = goodsService.reduceStock(goodsId);
        result.setData(flag);
        return result;
    }
}

package com.siwen.common.api;

import com.siwen.common.api.vo.GoodsVo;
import com.siwen.common.api.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author siwen
 * @Date 2021/5/17 9:38
 * @Description feigin服务调用
 **/
@FeignClient(name = "goods-service")
@RequestMapping("/goods")
public interface GoodsServiceApi {

    @GetMapping(value = "/getGoodsVoByGoodsId")
    Result<GoodsVo> getGoodsVoByGoodsId(@RequestParam("goodsId") String goodsId);

    @GetMapping(value = "/reduceStock")
    Result<Boolean> reduceStock(@RequestParam("goodsId") String goodsId);
}

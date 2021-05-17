package com.siwen.common.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author siwen
 * @Date 2021/5/12 17:21
 * @Description 商品  领域模型
 **/
@Data
public class Goods {
    private Long id;//id
    private String goodsName;//名称
    private String goodsTitle;//标题
    private String goodsImg;//图片
    private String goodsDetail;//详情
    private BigDecimal goodsPrice;//价格
    private Integer goodsStock;//库存
}

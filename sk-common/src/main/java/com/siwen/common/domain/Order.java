package com.siwen.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author siwen
 * @Date 2021/5/14 17:40
 * @Description 订单数据
 **/
@Data
public class Order {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}

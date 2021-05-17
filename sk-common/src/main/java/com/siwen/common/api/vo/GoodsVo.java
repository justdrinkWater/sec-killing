package com.siwen.common.api.vo;

import com.siwen.common.domain.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author siwen
 * @Date 2021/5/17 9:41
 * @Description 商品vo
 **/
@Data
public class GoodsVo extends Goods {
    private BigDecimal secKillingPrice; //秒杀价格
    private Integer secKillingStockCount;//秒杀数量
    private Date startDate;//开始时间
    private Date endDate;//结束时间
}

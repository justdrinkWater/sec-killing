package com.siwen.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author siwen
 * @Date 2021/5/13 14:46
 * @Description 预下单封装对象
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreOrder {

    //用户id
    private Long userId;

    //商品id
    private String goodsId;
}

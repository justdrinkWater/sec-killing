package com.siwen.common.constant;

import lombok.Getter;

/**
 * @Author siwen
 * @Date 16:41
 * @Description 该类为请求响应的结果
 * <p>
 * 1 打头 系统系列错误
 * 2 check 系列错误
 * 3 秒杀错误
 * 4 商品错误
 * 5 订单错误
 **/
@Getter
public enum ResultStatus {
    SUCCESS(0, "成功"),
    FAILD(-1, "失败"),
    EXCEPTION(-1, "系统异常"),

    PARAM_EXCEPTION(20001, "参数校验异常"),


    SALE_OVER(30001, "秒杀已结束"),
    REPEAT_SEC_KILLING(30002, "已抢到，请勿重复秒杀"),
    SEC_KILLING_FAIL(30003, "秒杀失败");

    /**
     * 商品模块
     */
    private int code;
    private String message;

    private ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

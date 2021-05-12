package com.siwen.seckilling.vo;

import com.siwen.seckilling.constant.ResultStatus;
import lombok.Getter;

/**
 * @Author siwen
 * @Date 2021/5/12 16:40
 * @Description 请求响应结果封装类
 **/
@Getter
public abstract class AbstractResult {
    private ResultStatus status;
    private int code;
    private String message;

    protected AbstractResult(ResultStatus status, String message) {
        this.code = status.getCode();
        this.status = status;
        this.message = message;
    }

    protected AbstractResult(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.status = status;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.status = resultStatus;
    }
}

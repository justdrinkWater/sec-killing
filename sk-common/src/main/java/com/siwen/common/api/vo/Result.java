package com.siwen.common.api.vo;

import com.siwen.common.constant.ResultStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author siwen
 * @Date 2021/5/12 16:44
 * @Description 具体的结果封装类，包括返回结果和个数
 **/
@Setter
@Getter
public class Result<T> extends AbstractResult implements Serializable {
    private T data; //返回的结果
    private Integer count; //返回结果的个数

    protected Result(ResultStatus status, String message) {
        super(status, message);
    }

    protected Result(ResultStatus status) {
        super(status);
    }

    public static <T> Result<T> buildSuccess() {
        return new Result<>(ResultStatus.SUCCESS, null);
    }

    public static <T> Result<T> build(ResultStatus status) {
        return new Result<>(status);
    }

    public boolean isSuccess() {
        return ResultStatus.SUCCESS == getStatus();
    }
}

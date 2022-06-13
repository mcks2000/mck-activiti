package com.mck.activiti.common.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 异常返回对象封装
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BizException extends RuntimeException {
    /**
     * 状态码
     */
    protected int code;
    /**
     * 返回信息
     */
    protected String msg;

    /**
     * 返回信息
     */
    protected String errMsg;

    public BizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException(String msg) {
        this.code = 400;
        this.msg = msg;
    }
}
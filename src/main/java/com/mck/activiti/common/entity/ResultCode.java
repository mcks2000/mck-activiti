package com.mck.activiti.common.entity;

/**
 * @Author mck
 * @Description Date: 2022/5/24 10:17
 * @Param
 * @return
 **/
public enum ResultCode {
    // 成功
    SUCCESS(200),

    // 未登录
    NOT_LOGIN(203),

    // 失败
    FAIL(400),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}

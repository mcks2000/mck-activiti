package com.mck.activiti.common.entity;

/**
 * @Description:返回结果集封装
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public class ResponseResult<T> {

    public int code; //返回状态码200成功

    private String msg; //返回描述信息

    private String errMsg; //返回描述信息

    private T data; //返回内容体

    public ResponseResult<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ResponseResult<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}

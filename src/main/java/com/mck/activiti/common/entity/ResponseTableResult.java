package com.mck.activiti.common.entity;

/**
 * @Description:TABLE结果集封装
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public class ResponseTableResult<T> {

    public int code; //返回状态码0成功

    private String msg; //返回描述信息

    private String errMsg; //返回描述信息

    private long count; //返回总条数

    private T data; //返回内容体

    public ResponseTableResult<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseTableResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseTableResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ResponseTableResult<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseTableResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseTableResult<T> setCount(long count) {
        this.count = count;
        return this;
    }

    public long getCount() {
        return count;
    }


}

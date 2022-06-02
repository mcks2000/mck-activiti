package com.mck.activiti.common.entity;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public class ResponseUtil {

    private final static String SUCC_MSG = "操作成功";
    private final static String ERR_MSG = "";

    public static <T> ResponseResult<T> makeOKRsp() {
        return makeOKRsp(null);
    }

    public static <T> ResponseResult<T> makeOKRsp(T data) {
        return makeOKRsp(ResultCode.SUCCESS.code, data);
    }

    public static <T> ResponseResult<T> makeOKRsp(int code, T data) {
        return new ResponseResult<T>().setCode(code).setMsg(SUCC_MSG).setData(data).setErrMsg(ERR_MSG);
    }


    public static <T> ResponseResult<T> makeErrRsp(int code, String msg) {
        return makeErrRsp(code, msg, ERR_MSG);
    }

    public static <T> ResponseResult<T> makeErrRsp(int code, String msg, String errMsg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg).setErrMsg(errMsg);
    }

    public static <T> ResponseTableResult<T> makeTableRsp(int code, long count, T data) {
        return new ResponseTableResult<T>().setCode(code).setCount(count).setMsg(SUCC_MSG).setData(data).setErrMsg(ERR_MSG);
    }
}

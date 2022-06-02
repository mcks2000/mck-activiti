package com.mck.activiti.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.common.entity.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ActivitiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Description: 全局异常捕获
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static String ERR_MSG = "系统繁忙请稍后重试";

    /**
     * 参数解析失败
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public <T> ResponseResult<T> badRequestException(IllegalArgumentException e) {
        log.error("参数解析失败：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "参数解析失败:" + e.getMessage(), ERR_MSG);
    }

    /**
     * 没有权限请求当前方法
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public <T> ResponseResult<T> badMethodExpressException(AccessDeniedException e) {
        log.error("没有权限请求当前方法：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "没有权限请求当前方法:" + e.getMessage(), ERR_MSG);
    }

    /**
     * 不支持当前请求方法
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public <T> ResponseResult<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "不支持当前请求方法:" + e.getMessage(), ERR_MSG);
    }

    /**
     * 不支持当前媒体类型
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public <T> ResponseResult<T> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "不支持当前媒体类型:" + e.getMessage(), ERR_MSG);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public <T> ResponseResult<T> handleSqlException(SQLException e) {
        log.error("服务运行SQLException异常：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "服务运行SQLException异常:" + e.getMessage(), ERR_MSG);
    }

    /**
     * 数字格式化异常
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NumberFormatException.class})
    public <T> ResponseResult<T> handleNumberFormatException(NumberFormatException e) {
        log.error("数字格式化异常：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "数字格式化异常:" + e.getMessage(), ERR_MSG);
    }

    /**
     * Error saving model
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ActivitiException.class})
    public <T> ResponseResult<T> handleActivitiException(ActivitiException e) {
        log.error("Error saving model：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "Error saving model:" + e.getMessage(), ERR_MSG);
    }

    /**
     * Error saving model
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class})
    public <T> ResponseResult<T> handleIOException(IOException e) {
        log.error("Error saving model：{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "IOException:" + e.getMessage(), ERR_MSG);
    }


    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BizException.class)
    public <T> ResponseResult<T> bizExceptionHandler(BizException e) {
        log.error("业务异常：{}", e.getMsg());
        return ResponseUtil.makeErrRsp(e.getCode(), "业务处理异常:" + e.getMsg(), this.errMsg(e));
    }

    /**
     * 设置异常msg
     *
     * @param e
     * @return
     */
    private String errMsg(BizException e) {
        return ObjectUtil.isNotEmpty(e.getErrMsg()) ? e.getErrMsg() : e.getMsg();
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public <T> ResponseResult<T> exceptionHandler(NullPointerException e) {
        log.error("空指针异常:{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.FAIL.code, "空指针异常:" + e.getMessage(), ERR_MSG);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public <T> ResponseResult<T> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("系统异常:{}", e);
        return ResponseUtil.makeErrRsp(ResultCode.INTERNAL_SERVER_ERROR.code, "系统异常:" + e.getMessage(), ERR_MSG);
    }
}

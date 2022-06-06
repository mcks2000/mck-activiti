package com.mck.activiti.service;

import com.mck.activiti.model.entity.ProcessLog;

import java.util.List;

/**
 * @Description: 日志服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface ILogService {

    /**
     * 日志记录
     *
     * @param processLog
     */
    void insertLog(ProcessLog processLog);

    /**
     * 查询历史单操作记录
     *
     * @param orderNo
     * @return
     */
    List<ProcessLog> queryOperLog(Long orderNo);
}

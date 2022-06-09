package com.mck.activiti.module.flow.service;

import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.ProcessLog;

import java.util.List;

/**
 * @Description: 日志服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IProcessLogService extends ISuperService<ProcessLog> {

    /**
     * @param processLog 审批日志
     * @Description 日志记录
     */
    void insertProcessLog(ProcessLog processLog);

    /**
     * @param orderNo 审批ID
     * @return
     * @Description 查询历史单操作记录
     */
    List<ProcessLog> queryProcessLog(Long orderNo);
}

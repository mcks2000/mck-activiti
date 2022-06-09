package com.mck.activiti.module.flow.manager;

import org.activiti.engine.task.Task;

import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IFlowManagerService {

    /**
     * 流程匹配服务
     *
     * @param orderId   审批单ID
     * @param variables 审核相关变量 eg.{"applyuser":"userId","subState":"success"}
     */
    String createProcessInstance(Long orderId, Map<String, Object> variables);

    /**
     * 启动流程
     *
     * @param flowDefId   流程规则Id
     * @param flowAuditId 流程审核Id
     * @param variables   审核相关变量 eg.{"applyuser":"userId","subState":"success"}
     */
    String executeCreateFlow(String flowDefId, String flowAuditId, Map<String, Object> variables);


    /**
     * 根据流程实例查询当前任务信息
     *
     * @param processInstanceId
     * @return
     */
    Task queryTaskByInstId(String processInstanceId);

}

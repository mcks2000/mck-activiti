package com.mck.activiti.module.flow.manager;

import org.activiti.engine.task.Task;

import java.util.Map;

/**
 * @Description: 流程管理服务，链接activiti和业务服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IFlowManagerService {

    /**
     * @param orderId   审批单ID
     * @param variables 审批相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @Description 流程匹配服务
     */
    String createProcessInstance(Long orderId, Map<String, Object> variables);

    /**
     * @param flowDefId   流程规则Id
     * @param flowAuditId 流程审批Id
     * @param variables   审批相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @Description 启动流程
     */
    String executeCreateFlow(String flowDefId, String flowAuditId, Map<String, Object> variables);


    /**
     * @param processInstanceId 流程实例ID
     * @return
     * @Description 根据流程实例查询当前任务信息
     */
    Task queryTaskByProcessInstanceId(String processInstanceId);
}

package com.mck.activiti.module.flow.service;

import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.FlowAudit;

import java.util.List;

/**
 * @version 1.0.1
 * @Description: com.mck.activiti.service
 * @Author: mck
 * @Date: 2022/06/09 19:38
 **/
public interface IFlowAuditService extends ISuperService<FlowAudit> {
    /**
     * @param flowAudit 流程规则
     * @Description 记录流转主表信息
     */
    void insertFlowAudit(FlowAudit flowAudit);

    /**
     * @param orderNo
     * @return
     * @Description 根据审批单号查询匹配流程信息
     */
    FlowAudit queryFlowAuditByOrderNo(Long orderNo);

    /**
     * @param flowAuditId 流程审批ID
     * @return
     * @Description 根据主键查询
     */
    FlowAudit queryFlowAuditById(String flowAuditId);

    /**
     * @param ruleId 流程规则ID
     * @return
     * @Description 根据流程规则ID查询
     */
    List<FlowAudit> queryFlowByRuleId(String ruleId);


}

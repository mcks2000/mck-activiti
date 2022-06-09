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
     * 记录流转主表信息
     *
     * @param flowAudit
     */
    void insertFlowAudit(FlowAudit flowAudit);

    /**
     * 记录流转主表信息
     *
     * @param flowAudit
     */
    void insertFlowAuditNoId(FlowAudit flowAudit);

    /**
     * 根据审批单号查询匹配流程信息
     *
     * @param orderNo
     * @return
     */
    FlowAudit queryFlowAuditByOrderNo(Long orderNo);

    /**
     * 根据主键查询
     *
     * @param flowAuditId
     * @return
     */
    FlowAudit queryFlowById(Long flowAuditId);

    List<FlowAudit> queryFlowByRuleId(String ruleId);

}

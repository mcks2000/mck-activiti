package com.mck.activiti.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.model.entity.FlowDef;
import com.mck.activiti.model.entity.FlowAudit;
import com.mck.activiti.model.entity.FlowRule;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IFlowInfoService {

    /**
     * 查询流程定义列表
     *
     * @return
     */
    List<FlowDef> queryFlowDefList();

    /**
     * 查询流程定义
     *
     * @param defId
     * @return
     */
    FlowDef queryFlowDef(Long defId);

    /**
     * 新增流程定义
     *
     * @param flowDef
     */
    void insertFlowDef(FlowDef flowDef);

    /**
     * 新增流程规则
     *
     * @param flowRule
     */
    String insertFlowRule(FlowRule flowRule);

    /**
     * 流程规则查询
     *
     * @return
     */
    Page<FlowRule> queryFlowRule(PageBean pageBean);

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
    String runFlow(String flowDefId, String flowAuditId, Map<String, Object> variables);

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
     * 根据流程实例查询当前任务信息
     *
     * @param processInstanceId
     * @return
     */
    Task queryTaskByInstId(String processInstanceId);

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

    void deleteFlowRuleById(String ruleId);

    List<FlowAudit> queryFlowByRuleId(String ruleId);
}

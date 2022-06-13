package com.mck.activiti.module.flow.manager.impl;

import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.common.util.ParamAssertUtil;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
import com.mck.activiti.module.flow.model.entity.FlowRule;
import com.mck.activiti.module.flow.model.entity.VacationOrder;
import com.mck.activiti.module.flow.manager.IFlowManagerService;
import com.mck.activiti.module.flow.service.IFlowAuditService;
import com.mck.activiti.module.flow.service.IFlowRuleService;
import com.mck.activiti.module.flow.service.IVacationOrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流程管理服务，链接activiti和业务服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class FlowManagerServiceImpl implements IFlowManagerService {

    @Autowired
    private IVacationOrderService vacationOrderService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IFlowAuditService flowAuditService;
    @Autowired
    private IFlowRuleService flowRuleService;


    /**
     * 1.判断申请单是否有匹配的流程规则
     * 2.执行创建流程实例
     * 3.记录流程信息
     *
     * @param orderId   审批单ID
     * @param variables 审批相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @return
     * @Description 创建流程实例
     */
    @Override
    public String createProcessInstance(Long orderId, Map<String, Object> variables) {
        //查询所有流程规则
        List<FlowRule> flowRules = flowRuleService.queryList();
        ParamAssertUtil.notEmpty(flowRules, "审批单号:" + orderId + ",未匹配到流程规则");

        //查询审批单信息
        VacationOrder vacationOrder = vacationOrderService.getById(orderId);
        //规则匹配
        FlowRule currFlowRule = null;
        for (FlowRule flowRule : flowRules) {
            if (CommonUtil.checkKeywordsTrue(flowRule.getSystemCode(), vacationOrder.getSystemCode())
                    && CommonUtil.checkKeywordsTrue(flowRule.getBusiType(), vacationOrder.getBusiType())) {
                currFlowRule = flowRule;
                break;
            }
        }
        ParamAssertUtil.notNull(flowRules, "审批单号:" + orderId + ",未匹配到流程规则");


        Long flowAuditId = CommonUtil.genId();
        String processId = this.executeCreateFlow(currFlowRule.getFlowCode(), String.valueOf(flowAuditId), variables);


        //记录流程主表信息
        FlowAudit flowAudit = new FlowAudit();
        flowAudit.setFlowAuditId(flowAuditId);
        flowAudit.setProcessId(Long.valueOf(processId));
        flowAudit.setOrderNo(orderId);
        flowAudit.setFlowDefId(String.valueOf(currFlowRule.getDefId()));
        flowAudit.setFlowCode(currFlowRule.getFlowCode());
        flowAudit.setRuleId(currFlowRule.getRuleId());
        flowAuditService.insertFlowAudit(flowAudit);
        //运行流程
        return processId;
    }

    /**
     * @param flowDefId   流程规则Id
     * @param flowAuditId 流程审批Id
     * @param variables   审批相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @return
     * @Description 执行创建流程实例
     */
    @Override
    @SneakyThrows
    public String executeCreateFlow(String flowDefId, String flowAuditId, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowDefId, flowAuditId, variables);
        return processInstance.getProcessInstanceId();
    }

    /**
     * @param processInstanceId 流程实例ID
     * @return
     * @Description 根据流程实例查询当前任务信息
     */
    @Override
    public Task queryTaskByProcessInstanceId(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        return task;
    }


}
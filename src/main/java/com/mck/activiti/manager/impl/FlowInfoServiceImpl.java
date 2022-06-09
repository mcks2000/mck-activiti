package com.mck.activiti.manager.impl;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.common.util.ParamAssertUtil;
import com.mck.activiti.model.entity.FlowDef;
import com.mck.activiti.model.entity.FlowAudit;
import com.mck.activiti.model.entity.FlowRule;
import com.mck.activiti.model.entity.VacationOrder;
import com.mck.activiti.mapper.FlowDefMapper;
import com.mck.activiti.mapper.FlowAuditMapper;
import com.mck.activiti.mapper.FlowRuleMapper;
import com.mck.activiti.manager.IFlowInfoService;
import com.mck.activiti.service.IVacationOrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流程管理服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class FlowInfoServiceImpl implements IFlowInfoService {

    @Autowired
    private FlowDefMapper flowDefMapper;
    @Autowired
    private FlowRuleMapper flowRuleMapper;
    @Autowired
    private FlowAuditMapper flowAuditMapper;
    @Autowired
    private IVacationOrderService vacationOrderService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Override
    public List<FlowDef> queryFlowDefList() {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_state", 0);
        return flowDefMapper.selectList(queryWrapper);
    }

    @Override
    public FlowDef queryFlowDef(Long defId) {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("def_id", defId);
        return flowDefMapper.selectOne(queryWrapper);
    }

    @Transactional
    @Override
    public void insertFlowDef(FlowDef flowDef) {
        String flowCode = flowDef.getFlowCode();
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FLOW_CODE", flowCode);
        Integer count = flowDefMapper.selectCount(queryWrapper);
        if (count <= 0) {
            flowDef.setDefId(CommonUtil.genId());
            flowDef.setFlowState(0);
            flowDefMapper.insert(flowDef);
        }

    }

    @Override
    public String insertFlowRule(FlowRule flowRule) {
        String resMsg = "";
        QueryWrapper<FlowRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("def_id", flowRule.getDefId());
        Integer count = flowRuleMapper.selectCount(queryWrapper);
        if (count <= 0) {
            flowRule.setRuleId(CommonUtil.genId());
            flowRule.setSystemCode(String.join(",", flowRule.getSystemIds()));
            flowRule.setBusiType(String.join(",", flowRule.getBusiTypes()));
            flowRuleMapper.insert(flowRule);
            return resMsg;
        } else {
            resMsg = "您选择的【" + flowRule.getDefName() + "】已经配置,请重新选择业务流程";
            return resMsg;
        }
    }

    @Override
    public Page<FlowRule> queryFlowRule(PageBean pageBean) {
        Page<FlowRule> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        Page<FlowRule> flowRulePage = flowRuleMapper.selectPage(page, null);
        return flowRulePage;
    }

    /**
     * 1.判断申请单是否有匹配的流程规则
     * 2.执行创建流程实例
     * 3.记录流程信息
     *
     * @param orderId   审批单ID
     * @param variables 审核相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @return
     * @Description 创建流程实例
     */
    @Override
    public String createProcessInstance(Long orderId, Map<String, Object> variables) {
        //查询所有流程规则
        List<FlowRule> flowRules = flowRuleMapper.selectList(null);
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
        String processId = this.runFlow(String.valueOf(currFlowRule.getDefId()), String.valueOf(flowAuditId), variables);


        //记录流程主表信息
        FlowAudit flowAudit = new FlowAudit();
        flowAudit.setFlowAuditId(flowAuditId);
        flowAudit.setProcessId(Long.valueOf(processId));
        flowAudit.setOrderNo(orderId);
        flowAudit.setFlowDefId(String.valueOf(currFlowRule.getDefId()));
        flowAudit.setRuleId(currFlowRule.getRuleId());
        this.insertFlowAuditNoId(flowAudit);
        //运行流程
        return processId;
    }

    /**
     * @param flowDefId   流程规则Id
     * @param flowAuditId 流程审核Id
     * @param variables   审核相关变量 eg.{"applyuser":"userId","subState":"success"}
     * @return
     * @Description 执行创建流程实例
     */
    @Override
    @SneakyThrows
    public String runFlow(String flowDefId, String flowAuditId, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowDefId, flowAuditId, variables);
        return processInstance.getProcessInstanceId();
    }

    @Override
    public void insertFlowAudit(FlowAudit flowAudit) {
        flowAudit.setFlowAuditId(CommonUtil.genId());
        flowAudit.setFlowState(1);
        flowAuditMapper.insert(flowAudit);
    }

    @Override
    public void insertFlowAuditNoId(FlowAudit flowAudit) {
        flowAudit.setFlowState(1);
        flowAuditMapper.insert(flowAudit);
    }


    @Override
    public Task queryTaskByInstId(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        return task;
    }

    @Override
    public FlowAudit queryFlowAuditByOrderNo(Long orderNo) {
        QueryWrapper<FlowAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        queryWrapper.last("limit 1");
        return flowAuditMapper.selectOne(queryWrapper);
    }

    @Override
    public FlowAudit queryFlowById(Long flowAuditId) {
        QueryWrapper<FlowAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_audit_id", flowAuditId);
        return flowAuditMapper.selectOne(queryWrapper);
    }

    /**
     * @param ruleId 流程规则ID
     * @Description 通过 {ruleId} 删除数据
     */
    @Override
    public void deleteFlowRuleById(String ruleId) {
        QueryWrapper<FlowRule> wrapper = new QueryWrapper<>();
        wrapper.eq("rule_id", ruleId);
        flowRuleMapper.delete(wrapper);
    }

    /**
     * @param ruleId 流程规则ID
     * @return List<FlowAudit>
     * @Description 通过 {ruleId} 查询流程与审批单表关联表
     */
    @Override
    public List<FlowAudit> queryFlowByRuleId(String ruleId) {
        QueryWrapper<FlowAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rule_id", ruleId);
        return flowAuditMapper.selectList(queryWrapper);
    }

}
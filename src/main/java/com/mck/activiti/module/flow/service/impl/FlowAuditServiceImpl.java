package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.module.flow.mapper.FlowAuditMapper;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
import com.mck.activiti.module.flow.service.IFlowAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 流程审批日志管理
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Service
public class FlowAuditServiceImpl extends SuperServiceImpl<FlowAuditMapper, FlowAudit> implements IFlowAuditService {
    @Override
    public void insertFlowAudit(FlowAudit flowAudit) {
        flowAudit.setFlowAuditId(CommonUtil.genId());
        flowAudit.setFlowState(1);
        baseMapper.insert(flowAudit);
    }

    @Override
    public void insertFlowAuditNoId(FlowAudit flowAudit) {
        flowAudit.setFlowState(1);
        baseMapper.insert(flowAudit);
    }

    @Override
    public FlowAudit queryFlowAuditByOrderNo(Long orderNo) {
        QueryWrapper<FlowAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        queryWrapper.last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public FlowAudit queryFlowById(Long flowAuditId) {
        QueryWrapper<FlowAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_audit_id", flowAuditId);
        return baseMapper.selectOne(queryWrapper);
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
        return baseMapper.selectList(queryWrapper);
    }
}

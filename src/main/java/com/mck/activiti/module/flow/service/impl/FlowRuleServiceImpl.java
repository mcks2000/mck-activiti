package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.module.flow.mapper.FlowRuleMapper;
import com.mck.activiti.module.flow.model.entity.FlowRule;
import com.mck.activiti.module.flow.service.IFlowRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: 流程审批日志管理
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Service
public class FlowRuleServiceImpl extends SuperServiceImpl<FlowRuleMapper, FlowRule> implements IFlowRuleService {
    @Override
    public String insertFlowRule(FlowRule flowRule) {
        String resMsg = "";
        QueryWrapper<FlowRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("def_id", flowRule.getDefId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count <= 0) {
            flowRule.setRuleId(CommonUtil.genId());
            flowRule.setSystemCode(String.join(",", flowRule.getSystemIds()));
            flowRule.setBusiType(String.join(",", flowRule.getBusiTypes()));
            baseMapper.insert(flowRule);
            return resMsg;
        } else {
            resMsg = "您选择的【" + flowRule.getDefName() + "】已经配置,请重新选择业务流程";
            return resMsg;
        }
    }

    @Override
    public Page<FlowRule> queryFlowRule(PageBean pageBean) {
        Page<FlowRule> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        Page<FlowRule> flowRulePage = baseMapper.selectPage(page, null);
        return flowRulePage;
    }

    /**
     * @param ruleId 流程规则ID
     * @Description 通过 {ruleId} 删除数据
     */
    @Override
    public void deleteFlowRuleById(String ruleId) {
        QueryWrapper<FlowRule> wrapper = new QueryWrapper<>();
        wrapper.eq("rule_id", ruleId);
        baseMapper.delete(wrapper);
    }
}

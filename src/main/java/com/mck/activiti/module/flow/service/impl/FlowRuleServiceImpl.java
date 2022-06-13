package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.ParamAssertUtil;
import com.mck.activiti.module.flow.mapper.FlowRuleMapper;
import com.mck.activiti.module.flow.model.entity.FlowRule;
import com.mck.activiti.module.flow.service.IFlowRuleService;
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
public class FlowRuleServiceImpl extends SuperServiceImpl<FlowRuleMapper, FlowRule> implements IFlowRuleService {
    @Override
    public String insertFlowRule(FlowRule flowRule) {
        String resMsg = "";
        ParamAssertUtil.isTrue(flowRuleDeployState(flowRule.getDefId()), "您选择的【" + flowRule.getDefName() + "】已经配置,请重新选择业务流程");
        flowRule.setSystemCode(String.join(",", flowRule.getSystemIds()));
        flowRule.setBusiType(String.join(",", flowRule.getBusiTypes()));
        baseMapper.insert(flowRule);
        return resMsg;
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
        baseMapper.deleteById(ruleId);
    }

    /**
     * @param defId 业务流程定义ID
     * @return true：未部署过，可执行部署；false：已部署过
     * @Description 是否已经部署过
     */
    private boolean flowRuleDeployState(Long defId) {
        Integer count = getFlowRuleCountById(defId);
        return count <= 0 ? true : false;
    }

    /**
     * @param defId 业务流程定义ID
     * @return
     * @Description 通过 {ruleId} 查询部署次数
     */
    private Integer getFlowRuleCountById(Long defId) {
        QueryWrapper<FlowRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("def_id", defId);
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * @param modelId activiti流程ID
     * @return List<FlowRule>
     * @Description 通过 {modelId} 查询流程ID是否被流程规则使用
     */
    @Override
    public List<FlowRule> queryFlowRuleByModelId(String modelId) {
        return baseMapper.queryFlowRuleByModelId(modelId);
    }


    @Override
    public List<FlowRule> queryList(){
        return baseMapper.queryList();
    }
}

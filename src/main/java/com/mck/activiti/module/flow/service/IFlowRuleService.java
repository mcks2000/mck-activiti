package com.mck.activiti.module.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.FlowRule;

/**
 * @version 1.0.1
 * @Description: com.mck.activiti.service
 * @Author: mck
 * @Date: 2022/06/09 19:38
 **/
public interface IFlowRuleService extends ISuperService<FlowRule> {
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


    void deleteFlowRuleById(String ruleId);

}

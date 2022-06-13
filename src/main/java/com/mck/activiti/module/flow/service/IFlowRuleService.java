package com.mck.activiti.module.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.FlowDef;
import com.mck.activiti.module.flow.model.entity.FlowRule;

import java.util.List;

/**
 * @version 1.0.1
 * @Description: com.mck.activiti.service
 * @Author: mck
 * @Date: 2022/06/09 19:38
 **/
public interface IFlowRuleService extends ISuperService<FlowRule> {
    /**
     * @param flowRule 流程规则
     * @Description 新增流程规则
     */
    String insertFlowRule(FlowRule flowRule);

    /**
     * @param pageBean 分页
     * @return
     * @Description 流程规则查询
     */
    Page<FlowRule> queryFlowRule(PageBean pageBean);


    /**
     * @param ruleId 流程规则ID
     * @Description 通过{ruleId}删除
     */
    void deleteFlowRuleById(String ruleId);

    /**
     * @param modelId activiti流程ID
     * @return List<FlowDef>
     * @Description 通过 {modelId} 查询流程ID是否被流程规则使用
     */
    List<FlowRule> queryFlowRuleByModelId(String modelId);

    List<FlowRule> queryList();
}

package com.mck.activiti.module.flow.mapper;

import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.entity.FlowRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程规则表 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface FlowRuleMapper extends SuperMapper<FlowRule> {

}

package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.model.entity.FlowRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程规则表 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface FlowRuleMapper extends SuperMapper<FlowRule> {

}

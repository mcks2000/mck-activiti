package com.mck.activiti.module.flow.mapper;

import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程与审批单表关联表 Mapper 接口
 * @Author mck
 * @Date 2020-06-02
 */
@Mapper
public interface FlowAuditMapper extends SuperMapper<FlowAudit> {

}

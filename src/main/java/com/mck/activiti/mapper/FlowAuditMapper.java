package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.model.entity.FlowAudit;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程与审批单表关联表 Mapper 接口
 * @Author mck
 * @Date 2020-06-02
 */
@Mapper
public interface FlowAuditMapper extends BaseMapper<FlowAudit> {

}

package com.mck.activiti.module.flow.mapper;

import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.entity.ProcessLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程审批日志表 Mapper 接口
 * @Author mck
 * @Date 2020-06-04
 */
@Mapper
public interface ProcessLogMapper extends SuperMapper<ProcessLog> {

}

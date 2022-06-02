package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.model.entity.ProcessLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 流程审批日志表 Mapper 接口
 * @Author mck
 * @since 2020-06-04
 */
@Mapper
public interface ProcessLogMapper extends BaseMapper<ProcessLog> {

}

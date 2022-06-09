package com.mck.activiti.module.flow.mapper;

import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.entity.ScheduledTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 定时任务 Mapper 接口
 * @Author mck
 * @Date 2020-06-09
 */
@Mapper
public interface ScheduledTaskMapper extends SuperMapper<ScheduledTask> {

}

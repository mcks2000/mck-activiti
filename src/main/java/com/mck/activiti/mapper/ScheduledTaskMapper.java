package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.model.entity.ScheduledTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 定时任务 Mapper 接口
 * @Author mck
 * @since 2020-06-09
 */
@Mapper
public interface ScheduledTaskMapper extends BaseMapper<ScheduledTask> {

}

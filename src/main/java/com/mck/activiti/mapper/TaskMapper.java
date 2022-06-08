package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.model.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Mapper
public interface TaskMapper extends SuperMapper<TaskVo> {

    /**
     * 查询我的代办任务
     *
     * @param userId
     * @return
     */
    Page<TaskVo> queryMyTask(Page<TaskVo> page, @Param("userId") String userId);

    /**
     * 查询审批单当前任务信息
     *
     * @param vacationId
     * @return
     */
    TaskVo queryTaskById(@Param("vacationId") Long vacationId);
}

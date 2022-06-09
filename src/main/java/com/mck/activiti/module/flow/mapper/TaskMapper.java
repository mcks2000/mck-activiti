package com.mck.activiti.module.flow.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 查询任务服务，查询时关联 ACT_RU_TASK
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Mapper
public interface TaskMapper extends SuperMapper<TaskVo> {

    /**
     * @param userId 用户ID
     *               该ID是 ACT_RU_TASK.ASSIGNEE_={userId}
     *               业务系统中userId需要和工作流的userId一致
     * @return
     * @Description 查询我的代办任务
     */
    Page<TaskVo> queryMyTask(Page<TaskVo> page, @Param("userId") String userId);

    /**
     * @param vacationId 流程实例ID
     *                   查询是需要关联工作流中实例ID
     * @return
     * @Description 查询审批单当前任务信息
     */
    TaskVo queryTaskByVacationId(@Param("vacationId") Long vacationId);
}

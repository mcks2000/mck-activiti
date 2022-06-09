package com.mck.activiti.module.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.vo.TaskVo;

/**
 * @Description: 流程任务服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface ITaskProceService extends ISuperService<TaskVo> {

    /**
     * @param pageBean 分页
     * @return
     * @Description 查询我的代办任务
     */
    Page<TaskVo> queryMyTask(PageBean pageBean);

    /**
     * @param vacationId 请假ID
     * @return
     * @Description 根据审批单号查询正在执行的流程任务
     */
    TaskVo queryTaskByVacationId(Long vacationId);

    /**
     * @param taskVo 任务
     * @Description 流程办理
     */
    void completeTask(TaskVo taskVo);
}

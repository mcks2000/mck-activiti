package com.mck.activiti.module.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.ScheduledTask;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IScheduledTaskService extends ISuperService<ScheduledTask> {
    /**
     * @param pageBean 分页
     * @return
     * @Description 列表查询
     */
    Page<ScheduledTask> queryList(PageBean pageBean);

    /**
     * @param scheduledTask 定时任务
     * @Description 新增任务
     */
    void addScheduledTask(ScheduledTask scheduledTask);

    /**
     * @param scheduledTask 定时任务
     * @Description 更新定时任务
     */
    void updateScheduledTask(ScheduledTask scheduledTask);

    /**
     * @param taskId 定时任务ID
     * @Description 删除任务
     */
    void delScheduledTaskById(String taskId);

    /**
     * @param scheduledTask 定时任务
     * @Description 更改状态
     */
    void updateScheduledTaskState(ScheduledTask scheduledTask);

    /**
     * @param taskId 定时任务ID
     * @return
     * @Description 任务查询
     */
    ScheduledTask queryScheduledTaskById(String taskId);
}

package com.mck.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.model.entity.ScheduledTask;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IScheduledTaskService {
    /**
     * 列表查询
     *
     * @param pageBean
     * @return
     */
    Page<ScheduledTask> queryList(PageBean pageBean);

    /**
     * 新增任务
     *
     * @param scheduledTask
     */
    void addTask(ScheduledTask scheduledTask);

    /**
     * 更新
     *
     * @param scheduledTask
     */
    void updateTask(ScheduledTask scheduledTask);

    /**
     * 删除任务
     *
     * @param taskId
     */
    void delTask(String taskId);

    /**
     * 更改状态
     *
     * @param scheduledTask
     */
    void updateState(ScheduledTask scheduledTask);

    /**
     * 任务查询
     *
     * @param taskId
     * @return
     */
    ScheduledTask queryScheduled(String taskId);
}

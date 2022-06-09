package com.mck.activiti.module.flow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.schedule.CronTaskRegistrar;
import com.mck.activiti.common.schedule.SchedulingRunnable;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.module.flow.model.entity.ScheduledTask;
import com.mck.activiti.module.flow.mapper.ScheduledTaskMapper;
import com.mck.activiti.module.flow.service.IScheduledTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 定时任务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class ScheduledTaskServiceImpl extends SuperServiceImpl<ScheduledTaskMapper, ScheduledTask> implements IScheduledTaskService {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public Page<ScheduledTask> queryList(PageBean pageBean) {
        Page<ScheduledTask> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        return baseMapper.selectPage(page, null);
    }

    @Override
    @Transactional
    public void addTask(ScheduledTask scheduledTask) {
        if (StrUtil.isNotBlank(scheduledTask.getTaskId())) {//编辑
            this.updateTask(scheduledTask);
        } else { //新增
            scheduledTask.setTaskId(String.valueOf(CommonUtil.genId()));
            baseMapper.insert(scheduledTask);
            if (scheduledTask.getTaskState() == 0) {
                log.info("----------->添加任务到线程....");
                SchedulingRunnable task = new SchedulingRunnable(scheduledTask.getClassName(), scheduledTask.getMethodName(), scheduledTask.getReqParams());
                cronTaskRegistrar.addCronTask(task, scheduledTask.getTaskCron());
            }
        }

    }

    @Override
    @Transactional
    public void updateTask(ScheduledTask scheduledTask) {
        QueryWrapper<ScheduledTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", scheduledTask.getTaskId());
        baseMapper.updateById(scheduledTask);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(scheduledTask.getClassName(), scheduledTask.getMethodName(), scheduledTask.getReqParams());
        cronTaskRegistrar.removeCronTask(task);
        if (scheduledTask.getTaskState() == 0) {
            //改为启用状态重新添加任务
            cronTaskRegistrar.addCronTask(task, scheduledTask.getTaskCron());
        }
    }

    @Override
    @Transactional
    public void delTask(String taskId) {
        ScheduledTask scheduledTask = this.queryScheduled(taskId);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(scheduledTask.getClassName(), scheduledTask.getMethodName(), scheduledTask.getReqParams());
        cronTaskRegistrar.removeCronTask(task);

        QueryWrapper<ScheduledTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", taskId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void updateState(ScheduledTask scheduledTask) {
        ScheduledTask bean = this.queryScheduled(scheduledTask.getTaskId());

        bean.setTaskState(scheduledTask.getTaskState());
        baseMapper.updateById(bean);
        //移除任务
        SchedulingRunnable task = new SchedulingRunnable(bean.getClassName(), bean.getMethodName(), bean.getReqParams());
        cronTaskRegistrar.removeCronTask(task);
        if (bean.getTaskState() == 0) {
            //改为启用状态重新添加任务
            cronTaskRegistrar.addCronTask(task, bean.getTaskCron());
        }
    }

    @Override
    public ScheduledTask queryScheduled(String taskId) {
        QueryWrapper<ScheduledTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", taskId);
        queryWrapper.last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }
}

package com.mck.activiti.common.schedule;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.module.flow.model.entity.ScheduledTask;
import com.mck.activiti.module.flow.mapper.ScheduledTaskMapper;
import com.mck.activiti.module.flow.service.IScheduledTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 系统启动加载数据库正常启动的定时任务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class SysJobRunner implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private IScheduledTaskService scheduledTaskService;

    @Override
    public void run(String... args) throws Exception {
        log.info("------------------>开始加载定时任务...");
        QueryWrapper<ScheduledTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_state", 0);
        List<ScheduledTask> taskList = scheduledTaskService.list(queryWrapper);
        if (IterUtil.isNotEmpty(taskList)) {
            for (ScheduledTask scheduledTask : taskList) {
                SchedulingRunnable task = new SchedulingRunnable(scheduledTask.getClassName(), scheduledTask.getMethodName(), scheduledTask.getTaskId());
                cronTaskRegistrar.addCronTask(task, scheduledTask.getTaskCron());
            }
        }

    }
}

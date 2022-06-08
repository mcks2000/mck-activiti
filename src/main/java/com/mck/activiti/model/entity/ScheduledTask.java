package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description:定时任务
 * @Author mck
 * @Date 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_scheduled_task")
public class ScheduledTask extends BaseObject {

    /**
     * 任务ID
     */
    @TableId("task_id")
    private String taskId;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求入参
     */
    private String reqParams;

    /**
     * cron表达式
     */
    private String taskCron;

    /**
     * 任务状态（0:启用 1:禁用 ）
     */
    private Integer taskState;
}

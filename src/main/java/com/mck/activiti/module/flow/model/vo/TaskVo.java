package com.mck.activiti.module.flow.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class TaskVo {

    /**
     * 请假单ID
     */
    private String vacationId;

    /**
     * 请假人ID
     */
    private String userId;

    /**
     * 请假开始时间
     */
    private LocalDate startTime;

    /**
     * 请假结束时间
     */
    private LocalDate endTime;

    /**
     * 请假原因
     */
    private String vacationContext;

    /**
     * 流程定义ID
     */
    private String flowDefId;

    /**
     * 流程实例ID
     */
    private String processId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 流程定义KEY
     */
    private String taskDefKey;

    /**
     * 办理人
     */
    private String assign;

    /**
     * 任务创建时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 审批类型 0:同意 1:驳回
     */
    private String approvalType;
    /**
     * 备注
     */
    private String remark;

}

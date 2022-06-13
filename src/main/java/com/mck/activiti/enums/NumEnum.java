package com.mck.activiti.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @version 1.0.1
 * @Description: 数值枚举
 * @Author: mck
 * @Date: 2022/06/13 11:34
 **/
@Getter
@AllArgsConstructor
public enum NumEnum {

    // 通用数值枚举
    ZERO(0, "One", "0"),
    ONE(1, "Two", "1"),
    TWO(2, "Three", "2"),

    // FlowAudit flowState 流程状态(1:正常,0:异常)
    ZERO_FLOW_AUDIT_STATE(0, "Normal", "异常"),
    ONE_FLOW_AUDIT_STATE(1, "Anomaly", "正常"),

    // FlowDef flowState 启用状态(0:启用 1:禁用)
    ZERO_FLOW_DEF_STATE(0, "Disable", "启用"),
    ONE_FLOW_DEF_STATE(1, "Enable", "禁用"),

    // ScheduledTask taskState 任务状态（0:启用 1:禁用 ）
    ZERO_TASK_STATE(0, "Disable", "禁用"),
    ONE_TASK_STATE(1, "Enable", "启用"),

    // VacationOrder vacationState 申请状态(0:待提交 1:审批中 2:已废弃 3:已完成)
    ZERO_VACATION_STATE(0, "SUBMITTED", "待提交"),
    ONE_VACATION_STATE(1, "REVIEW", "审批中"),
    TWO_VACATION_STATE(2, "OBSOLETE", "已废弃"),
    THREE_VACATION_STATE(3, "finish_end", "审批完成"),

    // TaskVo approvalType 审批类型 0:同意 1:驳回
    ZERO_APPROVAL_TYPE(0, "agree", "审批通过"),
    ONE_APPROVAL_TYPE(1, "reject", "审批未通过");



    public static final String desc = "通用数值枚举";
    public static final String desc_flow_audit_state = "流程状态(1:正常,0:异常)";
    public static final String desc_flow_def_state = "启用状态(0:启用 1:禁用)";
    public static final String desc_task_state = "任务状态（0:启用 1:禁用 ）";
    public static final String desc_vacation_state = "申请状态(0:待提交 1:审批中 2:已废弃 3:已完成)";

    private Integer num;
    private String code;
    private String name;


}

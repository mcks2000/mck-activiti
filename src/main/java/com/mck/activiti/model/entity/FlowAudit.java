package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @Description: 流程与审批单表关联表
 * @Author mck
 * @Date 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_flow_audit")
public class FlowAudit extends BaseObject {
    /**
     * 主键
     */
    @TableId("flow_audit_id")
    private Long flowAuditId;

    /**
     * 审批单号
     */
    private Long orderNo;

    /**
     * 流程定义编码(创建流程时设置的)
     */
    private String flowDefId;

    /**
     * 流程ID(启动流程时生成的编码)
     */
    private Long processId;

    /**
     * 流程规则ID
     */
    private Long ruleId;

    /**
     * 流程状态(1:正常,0:异常)
     */
    private Integer flowState;
}

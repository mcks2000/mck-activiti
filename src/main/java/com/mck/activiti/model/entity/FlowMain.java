package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 流程与审批单表关联表
 * @Author mck
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_flow_main")
public class FlowMain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("flow_inst_id")
    private Long flowInstId;

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
    private Long flowId;

    /**
     * 流程规则ID
     */
    private Long ruleId;

    /**
     * 流程状态(1:正常,0:异常)
     */
    private Integer flowState;

    /**
     * 流程启用时间
     */
    private Date createTime;


}

package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 流程定义表
 * @Author mck
 * @Date 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_flow_def")
public class FlowDef implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务流程定义ID
     */
    @TableId("def_id")
    private Long defId;

    /**
     * 流程编码(流程图的编码)
     */
    private String flowCode;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 状态(0:启用 1:禁用)
     */
    private Integer flowState;


}

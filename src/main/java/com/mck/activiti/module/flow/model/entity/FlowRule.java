package com.mck.activiti.module.flow.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 流程规则表
 * @Author mck
 * @Date 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_flow_rule")
public class FlowRule extends BaseObject {
    /**
     * 规则ID
     */
    @TableId("rule_id")
    private Long ruleId;

    /**
     * 业务流程定义ID
     */
    private Long defId;

    /**
     * 系统来源
     */
    private String systemCode;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则描述
     */
    private String ruleDesc;

    @TableField(exist = false)
    private List<String> systemIds;

    @TableField(exist = false)
    private List<String> busiTypes;

    @TableField(exist = false)
    private String defName;


}

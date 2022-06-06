package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 流程规则表
 * @Author mck
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_flow_rule")
public class FlowRule implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 对应 sys_dict 中的 dict_type_code = 10
     * 对应 sys_dict_type 中的 dict_type = 10
     */
    private String systemCode;

    /**
     * 业务类型
     * 对应 sys_dict 中的 dict_type_code = 20
     * 对应 sys_dict_type 中的 dict_type = 20
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

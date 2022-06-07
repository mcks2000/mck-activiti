package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 流程审批日志表
 * @Author mck
 * @Date 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_process_log")
public class ProcessLog extends BaseObject {

    /**
     * 日志ID
     */
    @TableId("log_id")
    private Long logId;

    /**
     * 审批单ID
     */
    private Long orderNo;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务key
     */
    private String taskKey;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 审批状态(对应流程变量值)
     */
    private String approvStatu;

    /**
     * 操作ID
     */
    private String operId;

    /**
     * 操作值
     */
    private String operValue;

//    /**
//     * 操作时间
//     */
//    private Date createTime;
//
//    /**
//     * 备注
//     */
//    private String remark;


}

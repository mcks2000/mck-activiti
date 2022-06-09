package com.mck.activiti.module.flow.model.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Description: 假期管理
 * @Author mck
 * @Date 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_vacation_order")
public class VacationOrder extends BaseObject {
    /**
     * ID
     */
    @TableId("vacation_id")
    private Long vacationId;

    /**
     * 请假人ID
     */
    private String userId;

    /**
     * 开始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATE_PATTERN)
    private LocalDate endTime;

    /**
     * 请假类型
     */
    private Integer vacationType;

    /**
     * 请假原因
     */
    private String vacationContext;

    /**
     * 申请状态(0:待提交 1:审核中 2:已废弃 3:已完成)
     */
    private Integer vacationState;

    /**
     * 系统来源
     */
    private String systemCode;

    /**
     * 业务类型
     */
    private String busiType;
}

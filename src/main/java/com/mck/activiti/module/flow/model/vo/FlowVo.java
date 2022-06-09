package com.mck.activiti.module.flow.model.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class FlowVo {
    /**
     * 部署ID
     */
    private String deployMentId;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 流程key
     */
    private String flowKey;
}

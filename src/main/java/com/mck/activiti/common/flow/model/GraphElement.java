package com.mck.activiti.common.flow.model;

import lombok.Data;

/**
 * @Description: 节点和连线的父类
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class GraphElement {
    /**
     * 实例id，历史的id.
     */
    private String id;

    /**
     * 节点名称，bpmn图形中的id.
     */
    private String name;
}

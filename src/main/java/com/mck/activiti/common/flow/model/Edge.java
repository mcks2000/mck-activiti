package com.mck.activiti.common.flow.model;

import lombok.Data;

/**
 * @Description: 连线
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class Edge extends GraphElement {
    /**
     * 起点.
     */
    private Node src;

    /**
     * 终点.
     */
    private Node dest;

    /**
     * 循环.
     */
    private boolean cycle;
}

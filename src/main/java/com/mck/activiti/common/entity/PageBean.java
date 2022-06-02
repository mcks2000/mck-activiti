package com.mck.activiti.common.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Data
public class PageBean {

    /**
     * 当前页码
     */
    private long page;

    /**
     * 每页显示条数
     */
    private long limit;
}

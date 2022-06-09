package com.mck.activiti.module.system.service;

import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface ISystemService {

    /**
     * 查询菜单列表
     *
     * @return
     */
    Map<String, Object> queryMenuList();
}

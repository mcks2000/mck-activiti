package com.mck.activiti.service;

import com.mck.activiti.model.entity.SysDict;

import java.util.List;
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

    /**
     * 查询字典信息
     *
     * @param dictTypeCode
     * @return
     */
    List<SysDict> querySysDictInfo(int dictTypeCode);
}

package com.mck.activiti.module.system.service;

import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.system.model.entity.SysDict;

import java.util.List;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface ISysDictService extends ISuperService<SysDict> {

    /**
     * 查询字典信息
     *
     * @param dictTypeCode
     * @return
     */
    List<SysDict> querySysDictInfo(int dictTypeCode);
}

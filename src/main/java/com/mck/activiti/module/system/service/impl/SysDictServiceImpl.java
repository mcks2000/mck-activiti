package com.mck.activiti.module.system.service.impl;

import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.module.system.mapper.SysDictMapper;
import com.mck.activiti.module.system.model.entity.SysDict;
import com.mck.activiti.module.system.service.ISysDictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
public class SysDictServiceImpl extends SuperServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Override
    public List<SysDict> querySysDictInfo(int dictTypeCode) {
        return baseMapper.querySysDictInfo(dictTypeCode);
    }


}

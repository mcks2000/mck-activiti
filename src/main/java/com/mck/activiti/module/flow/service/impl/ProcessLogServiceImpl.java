package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.module.flow.model.entity.ProcessLog;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.module.flow.mapper.ProcessLogMapper;
import com.mck.activiti.module.flow.service.IProcessLogService;
import com.mck.activiti.module.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 流程审批日志管理
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Service
public class ProcessLogServiceImpl extends SuperServiceImpl<ProcessLogMapper, ProcessLog> implements IProcessLogService {

    @Autowired
    private ISysUserService userService;

    @Override
    @Transactional
    public void insertLog(ProcessLog processLog) {
        SysUser currentSysUser = userService.getCurrentUser();
        processLog.setLogId(CommonUtil.genId());
        processLog.setOperId(currentSysUser.getUserName());
        baseMapper.insert(processLog);
    }

    @Override
    public List<ProcessLog> queryOperLog(Long orderNo) {
        QueryWrapper<ProcessLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }
}

package com.mck.activiti.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.model.entity.ProcessLog;
import com.mck.activiti.model.entity.User;
import com.mck.activiti.mapper.ProcessLogMapper;
import com.mck.activiti.service.ILogService;
import com.mck.activiti.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private ProcessLogMapper processLogMapper;
    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public void insertLog(ProcessLog processLog) {
        User currentUser = userService.getCurrentUser();
        processLog.setLogId(CommonUtil.genId());
        processLog.setCreateTime(DateUtil.date());
        processLog.setOperId(currentUser.getUserName());
        processLogMapper.insert(processLog);
    }

    @Override
    public List<ProcessLog> queryOperLog(Long vacationId) {
        QueryWrapper<ProcessLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vacation_id", vacationId);
        queryWrapper.orderByDesc("create_time");
        return processLogMapper.selectList(queryWrapper);
    }
}

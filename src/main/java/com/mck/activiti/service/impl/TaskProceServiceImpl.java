package com.mck.activiti.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.model.entity.ProcessLog;
import com.mck.activiti.model.vo.TaskVo;
import com.mck.activiti.model.entity.User;
import com.mck.activiti.mapper.TaskMapper;
import com.mck.activiti.service.ILogService;
import com.mck.activiti.service.ITaskProceService;
import com.mck.activiti.service.IUserService;
import com.mck.activiti.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class TaskProceServiceImpl implements ITaskProceService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IVacationOrderService vacationOrderService;
    @Autowired
    private ILogService logService;

    @Override
    public Page<TaskVo> queryMyTask(PageBean pageBean) {
        Page<TaskVo> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        User currentUser = userService.getCurrentUser();
        return taskMapper.queryMyTask(page, currentUser.getUserId());
    }

    @Override
    public TaskVo queryTaskById(Long vacationId) {
        return taskMapper.queryTaskById(vacationId);
    }

    @Override
    public void completeTask(TaskVo taskVo) {
        Map<String, Object> variables = new HashMap<>();
        String spState = "";
        String spContext = "";
        if (StrUtil.equals("0", taskVo.getApprovalType())) {//审核通过
            spState = SysConstant.APPROVAL_AGREE;
            spContext = "审批通过";
            variables.put("spState", spState);
        } else if (StrUtil.equals("1", taskVo.getApprovalType())) {//驳回
            vacationOrderService.updateState(Long.valueOf(taskVo.getVacationId()), SysConstant.SUBMITTED_STATE);
            spState = SysConstant.APPROVAL_REJECT;
            spContext = "审批未通过";
            variables.put("spState", spState);
        }
        taskService.complete(taskVo.getTaskId(), variables);

        //记录日志
        ProcessLog bean = new ProcessLog();
        User user = userService.getCurrentUser();
        bean.setOrderNo(Long.valueOf(taskVo.getVacationId()));
        bean.setTaskId(taskVo.getTaskId());
        bean.setTaskName(taskVo.getTaskName());
        bean.setTaskKey(taskVo.getTaskDefKey());
        bean.setApprovStatu(spState);
        bean.setOperValue(user.getUserName() + spContext + ",审批意见:" + taskVo.getRemark());
        logService.insertLog(bean);

    }
}

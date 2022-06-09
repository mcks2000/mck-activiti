package com.mck.activiti.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.model.entity.ProcessLog;
import com.mck.activiti.model.entity.SysUser;
import com.mck.activiti.model.vo.TaskVo;
import com.mck.activiti.mapper.TaskMapper;
import com.mck.activiti.service.IProcessLogService;
import com.mck.activiti.service.ITaskProceService;
import com.mck.activiti.service.ISysUserService;
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
    private ISysUserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IVacationOrderService vacationOrderService;
    @Autowired
    private IProcessLogService logService;

    @Override
    public Page<TaskVo> queryMyTask(PageBean pageBean) {
        Page<TaskVo> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        SysUser currentSysUser = userService.getCurrentUser();
        return taskMapper.queryMyTask(page, currentSysUser.getUserId());
    }

    @Override
    public TaskVo queryTaskById(Long vacationId) {
        return taskMapper.queryTaskById(vacationId);
    }

    //

    /**
     * TODO 1. 添加上一级是谁来审核，2.最后审核时间排在前面，判断如果是最后一位审核则自动跳过
     * <p>
     * 1.判断是审核是否通过
     * 1.1通过，流程往下继续走
     * 1.2不通过 更新审核状态，在走下一步
     *
     * @param taskVo
     */
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
        SysUser sysUser = userService.getCurrentUser();
        bean.setOrderNo(Long.valueOf(taskVo.getVacationId()));
        bean.setTaskId(taskVo.getTaskId());
        bean.setTaskName(taskVo.getTaskName());
        bean.setTaskKey(taskVo.getTaskDefKey());
        bean.setApprovStatu(spState);
        bean.setOperValue(sysUser.getUserName() + spContext + ",审批意见:" + taskVo.getRemark());
        logService.insertLog(bean);

    }
}

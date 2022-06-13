package com.mck.activiti.module.flow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.enums.NumEnum;
import com.mck.activiti.module.flow.model.entity.ProcessLog;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.module.flow.model.vo.TaskVo;
import com.mck.activiti.module.flow.mapper.TaskMapper;
import com.mck.activiti.module.flow.service.IProcessLogService;
import com.mck.activiti.module.flow.service.ITaskProceService;
import com.mck.activiti.module.system.service.ISysUserService;
import com.mck.activiti.module.flow.service.IVacationOrderService;
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
public class TaskProceServiceImpl extends SuperServiceImpl<TaskMapper, TaskVo> implements ITaskProceService {

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
        return baseMapper.queryMyTask(page, currentSysUser.getUserId());
    }

    @Override
    public TaskVo queryTaskByVacationId(Long vacationId) {
        return baseMapper.queryTaskByVacationId(vacationId);
    }

    /**
     * TODO 1. 添加上一级是谁来审批，2.最后审批时间排在前面，判断如果是最后一位审批则自动跳过
     * <p>
     * 1.判断是审批是否通过
     * 1.1通过，流程往下继续走
     * 1.2不通过 更新审批状态，在走下一步
     *
     * @param taskVo
     */
    @Override
    public void completeTask(TaskVo taskVo) {
        Map<String, Object> variables = new HashMap<>();
        String spState = "";
        String spContext = "";
        if (StrUtil.equals("0", taskVo.getApprovalType())) {//审批通过
            spState = NumEnum.ZERO_APPROVAL_TYPE.getCode();
            spContext = NumEnum.ZERO_APPROVAL_TYPE.getName();
            variables.put("spState", spState);
        } else if (StrUtil.equals("1", taskVo.getApprovalType())) {//驳回
            vacationOrderService.updateUpdateStateState(Long.valueOf(taskVo.getVacationId()), NumEnum.ZERO_VACATION_STATE.getNum());
            spState = NumEnum.ONE_APPROVAL_TYPE.getCode();
            spContext = NumEnum.ONE_APPROVAL_TYPE.getName();
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
        logService.insertProcessLog(bean);

    }
}

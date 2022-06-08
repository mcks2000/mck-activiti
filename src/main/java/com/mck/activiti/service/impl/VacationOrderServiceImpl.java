package com.mck.activiti.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.mapper.VacationOrderMapper;
import com.mck.activiti.model.entity.FlowAudit;
import com.mck.activiti.model.entity.ProcessLog;
import com.mck.activiti.model.entity.SysUser;
import com.mck.activiti.model.entity.VacationOrder;
import com.mck.activiti.model.vo.VacationOrderVo;
import com.mck.activiti.service.IFlowInfoService;
import com.mck.activiti.service.ILogService;
import com.mck.activiti.service.IUserService;
import com.mck.activiti.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class VacationOrderServiceImpl extends SuperServiceImpl<VacationOrderMapper, VacationOrder> implements IVacationOrderService {

    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ILogService logService;

    @Override
    @Transactional
    public void insertVacationOrder(VacationOrder vacationOrder) {
        //记录日志
        ProcessLog bean = new ProcessLog();
        SysUser currentSysUser = userService.getCurrentUser();
        if (null != vacationOrder.getVacationId()) {//更新
            this.updateById(vacationOrder);
            bean.setOrderNo(vacationOrder.getVacationId());
            bean.setOperValue(currentSysUser.getUserName() + "修改审批单");
        } else {
            long orderNo = CommonUtil.genId();
            bean.setOrderNo(orderNo);
            vacationOrder.setVacationId(orderNo);
            vacationOrder.setVacationState(0);
            vacationOrder.setUserId(currentSysUser.getUserId());
            vacationOrder.setSystemCode("1001");
            vacationOrder.setBusiType("2001");
            this.saveOrUpdate(vacationOrder);
            bean.setOperValue(currentSysUser.getUserName() + "填写审批单");
        }

        logService.insertLog(bean);
    }

    @Override
    public Page<VacationOrderVo> queryVacationOrder(PageBean pageBean) {
        Page<VacationOrder> page = new Page<>(pageBean.getPage(), pageBean.getLimit());
        SysUser currentSysUser = userService.getCurrentUser();
        Page<VacationOrderVo> vacationOrderPage = baseMapper.queryVacationOrder(page, currentSysUser.getUserId());
        return vacationOrderPage;
    }

    @Override
    public VacationOrder queryVacation(Long vacationId) {
        return this.getById(vacationId);
    }

    @Override
    @Transactional
    public void updateState(Long vacationId, Integer state) {
        VacationOrder vacationOrder = new VacationOrder();
        vacationOrder.setVacationState(state);
        vacationOrder.setVacationId(vacationId);
        this.updateById(vacationOrder);

    }

    @Override
    public boolean submitApply(Long vacationId) {
        boolean res = true;
        //匹配流程并指定申请人
        Map<String, Object> variables = new HashMap<>();
        SysUser currentSysUser = userService.getCurrentUser();
        String processId = "";
        //匹配流程之前查询是否已经匹配过
        FlowAudit flowAudit = flowInfoService.queryFlowAuditByOrderNo(vacationId);
        if (ObjectUtil.isNull(flowAudit)) {
            variables.put("applyuser", currentSysUser.getUserId());
            processId = flowInfoService.resolve(vacationId, variables);
        } else {
            processId = String.valueOf(flowAudit.getProcessId());
        }
        if (StrUtil.isBlank(processId)) {
            res = false;
            return res;
        }
        //流程流转，对应工作流提交成功
        Task task = flowInfoService.queryTaskByInstId(processId);
        if (ObjectUtil.isNull(task)) {
            res = false;
            return res;
        }
        variables.put("subState", "success");
        log.info("------------->当前办理任务ID:{}", task.getId());
        taskService.complete(task.getId(), variables);
        //更新审批单状态
        this.updateState(vacationId, SysConstant.REVIEW_STATE);

        // TODO 处理parentId 为空的逻辑
        //记录日志
        ProcessLog bean = new ProcessLog();
        SysUser sysUser = userService.queryUserById(currentSysUser.getParentUserId());
        bean.setOrderNo(vacationId);
        bean.setTaskId(task.getId());
        bean.setTaskName(task.getName());
        bean.setTaskKey(task.getTaskDefinitionKey());
        bean.setApprovStatu("submitApply");
        bean.setOperValue(currentSysUser.getUserName() + "提交申请,待【" + sysUser.getUserName() + "】审核");
        logService.insertLog(bean);
        return res;
    }

    @Override
    @Transactional
    public void delVacation(Long vacationId) {
        this.updateState(vacationId, SysConstant.OBSOLETE_STATE);
        //记录日志
        SysUser currentSysUser = userService.getCurrentUser();
        ProcessLog bean = new ProcessLog();
        SysUser sysUser = userService.queryUserById(currentSysUser.getParentUserId());
        bean.setOrderNo(vacationId);
        bean.setApprovStatu("DELETE");
        bean.setOperValue(currentSysUser.getUserName() + "删除审批单");
        logService.insertLog(bean);
    }

}

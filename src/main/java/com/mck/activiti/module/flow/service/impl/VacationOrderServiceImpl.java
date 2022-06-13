package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.common.util.ParamAssertUtil;
import com.mck.activiti.enums.NumEnum;
import com.mck.activiti.module.flow.mapper.VacationOrderMapper;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
import com.mck.activiti.module.flow.model.entity.ProcessLog;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.module.flow.model.entity.VacationOrder;
import com.mck.activiti.module.flow.model.vo.VacationOrderVo;
import com.mck.activiti.module.flow.manager.IFlowManagerService;
import com.mck.activiti.module.flow.service.IFlowAuditService;
import com.mck.activiti.module.flow.service.IProcessLogService;
import com.mck.activiti.module.system.service.ISysUserService;
import com.mck.activiti.module.flow.service.IVacationOrderService;
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
    private IFlowManagerService flowInfoService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IProcessLogService logService;
    @Autowired
    private IFlowAuditService flowAuditService;

    /**
     * 1.判断是否有上级
     * 2.保存或者更新
     * 3.插入审批日志
     *
     * @param vacationOrder
     */
    @Override
    @Transactional
    public void saveOrUpdateOrder(VacationOrder vacationOrder) {
        SysUser currentSysUser = this.parentUserIdIsNull();
        //记录日志
        ProcessLog bean = new ProcessLog();
        if (null != vacationOrder.getVacationId()) {//更新
            this.updateById(vacationOrder);
            bean.setOrderNo(vacationOrder.getVacationId());
            bean.setOperValue(currentSysUser.getUserName() + "修改审批单");
        } else {
            vacationOrder.setVacationState(NumEnum.ZERO_VACATION_STATE.getNum());
            vacationOrder.setUserId(currentSysUser.getUserId());

            // TODO 需要把用户角色完善后，才可自定义
            vacationOrder.setSystemCode("1001");//默认研发部
            vacationOrder.setBusiType("2001");//默认请假流程

            this.saveOrUpdate(vacationOrder);
            bean.setOrderNo(vacationOrder.getVacationId());
            bean.setOperValue(currentSysUser.getUserName() + "填写审批单");
        }

        logService.insertProcessLog(bean);
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
    public void updateUpdateStateState(Long vacationId, Integer state) {
        VacationOrder vacationOrder = new VacationOrder();
        vacationOrder.setVacationState(state);
        vacationOrder.setVacationId(vacationId);
        this.updateById(vacationOrder);

    }

    /**
     * 1.查询当前用户是否有上级 有：往下走？异常提醒
     *
     * @return
     * @Description 查询当前用户是否有上级
     */
    private SysUser parentUserIdIsNull() {
        SysUser currentSysUser = userService.getCurrentUser();
        ParamAssertUtil.notEmpty(currentSysUser.getParentUserId(), "无上级，无需发起请假");
        return currentSysUser;
    }

    /**
     * 1.判断当前用户是否有上级  是：返回true？往下走
     * 1.1返回true  请求结束  无上级，无需发起请假
     * 2.判断该流程是否已经发起申请 是：返回true？往下走
     * 2.1返回true  请求结束
     * 2.2创建流程实例，并将流程实例添加到 工作流任务中
     * 3.将流程审批流转到上一级
     * 4.更新审批状态，增加审批日志
     *
     * @param vacationId 审批单ID
     * @return
     * @Description 提交流程
     */
    @Override
    @Transactional
    public boolean submitVacationApply(Long vacationId) {
        SysUser currentSysUser = this.parentUserIdIsNull();

        //匹配流程之前查询是否已经匹配过
        FlowAudit flowAudit = flowAuditService.queryFlowAuditByOrderNo(vacationId);
        ParamAssertUtil.isEmpty(flowAudit, "无上级，无需发起请假");


        //匹配流程并指定申请人
        Map<String, Object> variables = new HashMap<>();
        variables.put("applyuser", currentSysUser.getUserId());
        String processId = flowInfoService.createProcessInstance(vacationId, variables);


        // 将流程审批流转到上一级
        Task task = flowInfoService.queryTaskByProcessInstanceId(processId);
        ParamAssertUtil.notNull(task, "任务未创建成功，请稍后再试");

        variables.put("subState", "success");
        taskService.complete(task.getId(), variables);


        //更新审批单状态
        this.updateUpdateStateState(vacationId, NumEnum.ONE_VACATION_STATE.getNum());
        //记录日志
        ProcessLog bean = new ProcessLog();
        SysUser sysUser = userService.queryUserById(currentSysUser.getParentUserId());
        bean.setOrderNo(vacationId);
        bean.setTaskId(task.getId());
        bean.setTaskName(task.getName());
        bean.setTaskKey(task.getTaskDefinitionKey());
        bean.setApprovStatu("submitApply");
        bean.setOperValue(currentSysUser.getUserName() + "提交申请,待【" + sysUser.getUserName() + "】审批");
        logService.insertProcessLog(bean);
        return true;
    }


    @Override
    @Transactional
    public void delVacationById(Long vacationId) {
        this.updateUpdateStateState(vacationId, NumEnum.TWO_VACATION_STATE.getNum());
        //记录日志
        SysUser currentSysUser = userService.getCurrentUser();
        ProcessLog bean = new ProcessLog();
//        SysUser sysUser = userService.queryUserById(currentSysUser.getParentUserId());
        bean.setOrderNo(vacationId);
        bean.setApprovStatu("DELETE");
        bean.setOperValue(currentSysUser.getUserName() + "删除审批单");
        logService.insertProcessLog(bean);
    }

}

package com.mck.activiti.listener;

import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.util.SpringUtils;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
import com.mck.activiti.module.flow.model.entity.ProcessLog;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.module.flow.service.IFlowAuditService;
import com.mck.activiti.module.flow.service.IProcessLogService;
import com.mck.activiti.module.system.service.ISysUserService;
import com.mck.activiti.module.flow.service.IVacationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @Description: 审批完成
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
public class FinishDelegate implements JavaDelegate {

    private static IFlowAuditService flowAuditService = SpringUtils.getBean(IFlowAuditService.class);
    private static IVacationOrderService vacationOrderService = SpringUtils.getBean(IVacationOrderService.class);
    private static IProcessLogService logService = SpringUtils.getBean(IProcessLogService.class);
    private static ISysUserService userService = SpringUtils.getBean(ISysUserService.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String flowAuditId = execution.getProcessBusinessKey();
        log.info("审批完成更新审批状态:{}", flowAuditId);

        FlowAudit flowAudit = flowAuditService.queryFlowAuditById(flowAuditId);
        vacationOrderService.updateUpdateStateState(flowAudit.getOrderNo(), SysConstant.COMPLETED_STATE);
        //记录日志
        ProcessLog bean = new ProcessLog();
        SysUser sysUser = userService.getCurrentUser();
        bean.setOrderNo(flowAudit.getOrderNo());
        bean.setTaskName("审批完成");
        bean.setTaskKey("finish_end");
        bean.setApprovStatu("finish_end");
        bean.setOperValue("审批完工");
        logService.insertProcessLog(bean);
    }
}

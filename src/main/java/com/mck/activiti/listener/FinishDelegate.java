package com.mck.activiti.listener;

import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.util.SpringUtils;
import com.mck.activiti.model.entity.FlowMain;
import com.mck.activiti.model.entity.ProcessLog;
import com.mck.activiti.model.entity.User;
import com.mck.activiti.service.IFlowInfoService;
import com.mck.activiti.service.ILogService;
import com.mck.activiti.service.IUserService;
import com.mck.activiti.service.IVacationOrderService;
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
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String flowInstId = execution.getProcessBusinessKey();
        log.info("审批完成更新审批状态:{}", flowInstId);
        IVacationOrderService vacationOrderService = SpringUtils.getBean(IVacationOrderService.class);
        IFlowInfoService flowInfoService = SpringUtils.getBean(IFlowInfoService.class);
        FlowMain flowMain = flowInfoService.queryFlowById(Long.valueOf(flowInstId));
        vacationOrderService.updateState(flowMain.getOrderNo(), SysConstant.COMPLETED_STATE);
        //记录日志
        ILogService logService = SpringUtils.getBean(ILogService.class);
        IUserService userService = SpringUtils.getBean(IUserService.class);
        ProcessLog bean = new ProcessLog();
        User user = userService.getCurrentUser();
        User userInfo = userService.queryUserById(user.getUserId());
        bean.setOrderNo(flowMain.getOrderNo());
        bean.setTaskName("审批完成");
        bean.setTaskKey("finish_end");
        bean.setApprovStatu("finish_end");
        bean.setOperValue("审批完工");
        logService.insertLog(bean);
    }
}

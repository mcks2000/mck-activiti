package com.mck.activiti.module.flow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.VacationOrder;
import com.mck.activiti.module.flow.model.vo.VacationOrderVo;

/**
 * @Description: 请假服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IVacationOrderService extends ISuperService<VacationOrder> {

    /**
     * @param vacationOrder 假期
     * @Description 提交请假申请
     */
    void saveOrUpdateOrder(VacationOrder vacationOrder);

    /**
     * @param pageBean 分页
     * @return
     * @Description 请假单列表查询
     */
    Page<VacationOrderVo> queryVacationOrder(PageBean pageBean);

    /**
     * @param vacationId 假期ID
     * @return
     * @Description 根据审批单号查询审批信息
     */
    VacationOrder queryVacation(Long vacationId);

    /**
     * @param vacationId 假期ID
     * @param state      审批状态
     * @Description 更新审批单状态
     * (0:待提交 1:审批中 2:已废弃 3:已完成)
     */
    void updateUpdateStateState(Long vacationId, Integer state);

    /**
     * @param vacationId 假期ID
     * @Description 提交申请
     * 1.申请成功更改状态为已提交审批中
     * 2.执行工作流
     */
    boolean submitVacationApply(Long vacationId);

    /**
     * @param vacationId 假期ID
     * @Description 删除审批单
     */
    void delVacationById(Long vacationId);
}

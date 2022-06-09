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
     * 提交请假申请
     *
     * @param vacationOrder
     */
    void saveOrUpdateOrder(VacationOrder vacationOrder);

    /**
     * 请假单列表查询
     *
     * @param pageBean
     * @return
     */
    Page<VacationOrderVo> queryVacationOrder(PageBean pageBean);

    /**
     * 根据审批单号查询审批信息
     *
     * @param vacationId
     * @return
     */
    VacationOrder queryVacation(Long vacationId);

    /**
     * 更新审批单状态
     * (0:待提交 1:审核中 2:已废弃 3:已完成)
     *
     * @param vacationId
     */
    void updateState(Long vacationId, Integer state);

    /**
     * 提交申请
     * 1.申请成功更改状态为已提交审核中
     * 2.执行工作流
     *
     * @param vacationId
     */
    boolean submitApply(Long vacationId);

    /**
     * 删除审批单
     *
     * @param vacationId
     */
    void delVacation(Long vacationId);
}

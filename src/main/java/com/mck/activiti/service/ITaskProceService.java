package com.mck.activiti.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.model.vo.TaskVo;

/**
 * @Description: 流程任务服务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface ITaskProceService {

    /**
     * 查询我的代办任务
     *
     * @return
     */
    Page<TaskVo> queryMyTask(PageBean pageBean);

    /**
     * 根据审批单号查询正在执行的流程任务
     *
     * @param vacationId
     * @return
     */
    TaskVo queryTaskById(Long vacationId);

    /**
     * 流程办理
     *
     * @param taskVo
     */
    void completeTask(TaskVo taskVo);
}

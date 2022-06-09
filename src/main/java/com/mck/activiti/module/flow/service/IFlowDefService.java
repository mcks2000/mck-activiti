package com.mck.activiti.module.flow.service;

import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.FlowDef;

import java.util.List;

/**
 * @version 1.0.1
 * @Description: com.mck.activiti.service
 * @Author: mck
 * @Date: 2022/06/09 19:38
 **/
public interface IFlowDefService extends ISuperService<FlowDef> {
    /**
     * 查询流程定义列表
     *
     * @return
     */
    List<FlowDef> queryFlowDefList();

    /**
     * 查询流程定义
     *
     * @param defId
     * @return
     */
    FlowDef queryFlowDefById(Long defId);

    /**
     * 新增流程定义
     *
     * @param flowDef
     */
    void insertFlowDef(FlowDef flowDef);
}

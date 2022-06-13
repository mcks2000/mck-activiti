package com.mck.activiti.module.flow.service;

import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.flow.model.entity.FlowAudit;
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
    void saveOrUpdateFlowDef(FlowDef flowDef);

    /**
     * @param modelId      流程ID
     * @param deploymentId 部署ID
     * @Description 根据 {modelId} 更新 {deploymentId}
     */
    void updateByModeId(String modelId, String deploymentId);

    /**
     * @param flowCode 流程编码(流程图的编码)
     * @param flowName 流程名称
     * @return
     * @Description 通过 {flowCode} 查询部署列表
     */
    List<FlowDef> queryFlowDefListByFlowCode(String flowCode, String flowName);

    /**
     * @param modelId activiti流程ID
     * @return List<FlowDef>
     * @Description 通过 {modelId} 查询流程ID是否已部署
     */
    List<FlowDef> queryFlowDefByModelId(String modelId);
}

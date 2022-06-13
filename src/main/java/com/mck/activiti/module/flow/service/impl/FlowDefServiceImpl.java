package com.mck.activiti.module.flow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.enums.NumEnum;
import com.mck.activiti.module.flow.mapper.FlowDefMapper;
import com.mck.activiti.module.flow.model.entity.FlowDef;
import com.mck.activiti.module.flow.service.IFlowDefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 流程审批日志管理
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Service
public class FlowDefServiceImpl extends SuperServiceImpl<FlowDefMapper, FlowDef> implements IFlowDefService {
    @Override
    public List<FlowDef> queryFlowDefList() {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_state", NumEnum.ZERO_FLOW_DEF_STATE.getCode());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public FlowDef queryFlowDefById(Long defId) {
        return this.getById(defId);
    }


    @Override
    public void saveOrUpdateFlowDef(FlowDef flowDef) {
        String modelId= flowDef.getModelId();
        List<FlowDef> flowDefList = this.queryFlowDefByModelId(modelId);
        if (ObjectUtil.isNotEmpty(flowDefList)){
            UpdateWrapper<FlowDef> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("model_id", modelId);
            baseMapper.update(flowDef,updateWrapper);
        }else {
            flowDef.setFlowState(NumEnum.ZERO_FLOW_DEF_STATE.getNum());
            baseMapper.insert(flowDef);
        }


    }

    /**
     * @param modelId      流程ID
     * @param deploymentId 部署ID
     * @Description 根据 {modelId} 更新 {deploymentId}
     */
    @Override
    public void updateByModeId(String modelId, String deploymentId) {
        UpdateWrapper<FlowDef> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("model_id", modelId);
        updateWrapper.set("deployment_id", deploymentId);
        this.update(updateWrapper);
    }

    /**
     * @param flowCode 流程编码(流程图的编码)
     * @param flowName 流程名称
     * @return
     * @Description 通过 {flowCode} 查询部署列表
     */
    @Override
    public List<FlowDef> queryFlowDefListByFlowCode(String flowCode, String flowName) {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_code", flowCode).or().eq("flow_name", flowName);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * @param modelId activiti流程ID
     * @return List<FlowDef>
     * @Description 通过 {modelId} 查询流程ID是否已部署
     */
    @Override
    public List<FlowDef> queryFlowDefByModelId(String modelId) {
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model_id", modelId);
        return baseMapper.selectList(queryWrapper);
    }
}

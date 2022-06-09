package com.mck.activiti.module.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.common.util.CommonUtil;
import com.mck.activiti.module.flow.mapper.FlowDefMapper;
import com.mck.activiti.module.flow.model.entity.FlowDef;
import com.mck.activiti.module.flow.service.IFlowDefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        queryWrapper.eq("flow_state", 0);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public FlowDef queryFlowDefById(Long defId) {
        return this.getById(defId);
    }

    @Transactional
    @Override
    public void insertFlowDef(FlowDef flowDef) {
        String flowCode = flowDef.getFlowCode();
        QueryWrapper<FlowDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_code", flowCode);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count <= 0) {
            flowDef.setDefId(CommonUtil.genId());
            flowDef.setFlowState(0);
            baseMapper.insert(flowDef);
        }

    }
}

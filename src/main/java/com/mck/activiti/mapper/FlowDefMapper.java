package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.model.entity.FlowDef;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:流程定义表 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface FlowDefMapper extends BaseMapper<FlowDef> {

}

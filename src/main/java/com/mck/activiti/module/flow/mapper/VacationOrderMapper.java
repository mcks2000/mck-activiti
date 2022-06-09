package com.mck.activiti.module.flow.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.module.flow.model.entity.VacationOrder;
import com.mck.activiti.module.flow.model.vo.VacationOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 假期管理 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface VacationOrderMapper extends SuperMapper<VacationOrder> {

    Page<VacationOrderVo> queryVacationOrder(Page<VacationOrder> page, @Param("userId") String userId);

}

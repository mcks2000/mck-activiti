package com.mck.activiti.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.model.entity.VacationOrder;
import com.mck.activiti.model.vo.VacationOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 假期管理 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface VacationOrderMapper extends BaseMapper<VacationOrder> {

    Page<VacationOrderVo> queryVacationOrder(Page<VacationOrder> page, @Param("userId") String userId);

}

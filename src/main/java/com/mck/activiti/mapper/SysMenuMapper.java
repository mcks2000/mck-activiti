package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 系统菜单表 Mapper 接口
 * @Author mck
 * @Date 2020-05-28
 */
@Mapper
public interface SysMenuMapper extends SuperMapper<SysMenu> {

}

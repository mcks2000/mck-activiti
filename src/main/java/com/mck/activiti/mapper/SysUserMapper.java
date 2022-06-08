package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: Mapper 接口
 * @Author mck
 * @Date 2020-05-18
 */
@Repository
@Mapper
public interface SysUserMapper extends SuperMapper<SysUser> {

}

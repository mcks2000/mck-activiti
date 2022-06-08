package com.mck.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mck.activiti.common.mapper.SuperMapper;
import com.mck.activiti.model.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 字典类型值表 Mapper 接口
 * @Author mck
 * @Date 2020-06-01
 */
@Mapper
public interface SysDictMapper extends SuperMapper<SysDict> {

    /**
     * 根据字典类型编码查询字典信息
     *
     * @param dictTypeCode
     * @return
     */
    @Select("SELECT t1.*,t2.dict_type_name from sys_dict t1,sys_dict_type t2 WHERE t1.dict_type_code = t2.dict_type_code and t1.dict_type_code = #{dictTypeCode}")
    List<SysDict> querySysDictInfo(int dictTypeCode);
}

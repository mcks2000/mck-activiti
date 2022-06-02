package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 字典类型值表
 * @Author mck
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @TableId("dict_id")
    private String dictId;

    /**
     * 类型编码
     */
    private String dictTypeCode;

    /**
     * 字典名(展示用)
     */
    private String dictName;

    /**
     * 字典值
     */
    private String dictValue;

    @TableField(exist = false)
    private String dictTypeName;

}

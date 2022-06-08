package com.mck.activiti.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mck.activiti.common.entity.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 *
 * @Author mck
 * @Date 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseObject {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;

    private String parentUserId;

    private String userName;

    private String userPass;

    private String userPhoto;

    private String userNick;

    private String userSign;

    @TableField(exist = false)
    private String resMsg;

}

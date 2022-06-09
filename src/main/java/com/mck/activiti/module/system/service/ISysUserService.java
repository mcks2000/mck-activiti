package com.mck.activiti.module.system.service;

import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.service.ISuperService;
import com.mck.activiti.module.system.model.entity.SysUser;

/**
 * @Description: 服务类
 * @Author mck
 * @Date 2020-05-18
 */
public interface ISysUserService extends ISuperService<SysUser> {

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    SysUser getCurrentUser();

    /**
     * 根据用户名查询用户信息
     *
     * @param userId
     * @return
     */
    SysUser queryUserById(String userId);

    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    ResponseResult<SysUser> doLogin(String userName, String passWord);


}

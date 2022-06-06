package com.mck.activiti.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.common.entity.ResultCode;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.model.entity.SysUser;
import com.mck.activiti.mapper.UserMapper;
import com.mck.activiti.service.ICacheService;
import com.mck.activiti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 服务实现类
 * @Author mck
 * @since 2020-05-18
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ICacheService cacheService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser getCurrentUser() {
        Cookie cookie = ServletUtil.getCookie(request, SysConstant.ACTIVITI_COOKIE.toLowerCase());
        if (ObjectUtil.isNull(cookie)) {
            return null;
        }
        String loginToken = cookie.getValue();
        SysUser cacheSysUser = (SysUser) cacheService.getObjCacheByCode(loginToken);
        if (ObjectUtil.isNull(cacheSysUser)) {
            return null;
        }
        return cacheSysUser;
    }

    @Override
    public SysUser queryUserById(String userId) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public ResponseResult<SysUser> doLogin(String userName, String passWord) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNull(sysUser)) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code, "用户不存在", "用户不存在");
        }
        if (!StrUtil.equals(sysUser.getUserPass(), SecureUtil.md5(passWord))) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code, "用户名或密码错误", "用户名或密码错误");
        }
        return ResponseUtil.makeOKRsp(sysUser);
    }

}

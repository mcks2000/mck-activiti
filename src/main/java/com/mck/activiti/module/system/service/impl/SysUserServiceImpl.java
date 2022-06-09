package com.mck.activiti.module.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.config.exception.BizException;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.common.entity.ResultCode;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.service.impl.SuperServiceImpl;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.module.system.mapper.SysUserMapper;
import com.mck.activiti.common.service.ICacheService;
import com.mck.activiti.module.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 服务实现类
 * @Author mck
 * @Date 2020-05-18
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ICacheService cacheService;

    @Override
    public SysUser getCurrentUser() {
        Cookie cookie = ServletUtil.getCookie(getRequest(), SysConstant.ACTIVITI_COOKIE.toLowerCase());
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
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public ResponseResult<SysUser> doLogin(String userName, String passWord) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        SysUser sysUser = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNull(sysUser)) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code, "用户不存在", "用户不存在");
        }
        if (!StrUtil.equals(sysUser.getUserPass(), SecureUtil.md5(passWord))) {
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code, "用户名或密码错误", "用户名或密码错误");
        }
        return ResponseUtil.makeOKRsp(sysUser);
    }

    /**
     * 获取请求
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.info("请求上下文异常");
            throw new BizException("请求上下文异常");
        }
        return requestAttributes.getRequest();
    }

}

package com.mck.activiti.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.common.entity.ResultCode;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.common.util.CookieUtil;
import com.mck.activiti.module.system.model.entity.SysUser;
import com.mck.activiti.common.service.ICacheService;
import com.mck.activiti.module.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@RestController
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ICacheService cacheService;

    /**
     * 登录
     *
     * @param sysUser
     * @param response
     * @return
     */
    @PostMapping("login")
    public ResponseResult<SysUser> doLogin(@RequestBody SysUser sysUser, HttpServletResponse response) {
        ResponseResult<SysUser> userResponseResult = userService.doLogin(sysUser.getUserName(), sysUser.getUserPass());
        String token = IdUtil.fastSimpleUUID();
        ServletUtil.addCookie(response, SysConstant.ACTIVITI_COOKIE, token, -1); //关闭浏览器登录失效
        cacheService.cacheObjData(token, userResponseResult.getData(), 60);
        return userResponseResult;
    }


    /**
     * 获取登录信息
     *
     * @return
     */
    @PostMapping("getLoginInfo")
    public ResponseResult<SysUser> getLoginInfo() {
        SysUser currentSysUser = userService.getCurrentUser();
        if (ObjectUtil.isNull(currentSysUser)) {
            return ResponseUtil.makeErrRsp(ResultCode.NOT_LOGIN.code, "登录失效", "登录失效");
        }
        return ResponseUtil.makeOKRsp(currentSysUser);
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/outLogin")
    public ResponseResult<Object> outLogin(HttpServletRequest request, HttpServletResponse response) {
        //获取token
        String cookieStr = CookieUtil.getCookieStr(request);
        boolean delCacheRes = cacheService.delCacheByCode(cookieStr);
        log.info("删除缓存key:" + cookieStr + "，返回结果:" + delCacheRes);
        CookieUtil.delCookie(response, cookieStr);
        return ResponseUtil.makeOKRsp("注销成功");
    }
}

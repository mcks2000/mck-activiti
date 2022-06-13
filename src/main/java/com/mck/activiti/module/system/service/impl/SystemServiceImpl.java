package com.mck.activiti.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mck.activiti.common.util.TreeUtil;
import com.mck.activiti.module.system.model.vo.MenuVo;
import com.mck.activiti.module.system.model.entity.SysMenu;
import com.mck.activiti.module.system.service.ISysMenuService;
import com.mck.activiti.module.system.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public Map<String, Object> queryMenuList() {

        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> home = new HashMap<>(16);
        Map<String, Object> logo = new HashMap<>(16);

        //查询状态为启用的菜单
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.orderByAsc("sort");
        List<SysMenu> menuList = sysMenuService.list(queryWrapper);

        List<MenuVo> menuInfo = new ArrayList<>();
        for (SysMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getId());
            menuVO.setPid(e.getPid());
            menuVO.setHref(e.getHref());
            menuVO.setTitle(e.getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setTarget(e.getTarget());
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0L));
        home.put("title", "首页");
        home.put("href", "/page/welcome-1");//控制器路由,自行定义
        logo.put("title", "activiti工作流");
        logo.put("image", "images/logo.png");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo", home);
        map.put("logoInfo", logo);
        return map;
    }
}

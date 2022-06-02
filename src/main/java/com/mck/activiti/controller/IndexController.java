package com.mck.activiti.controller;

import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.common.entity.SysConstant;
import com.mck.activiti.model.entity.SysDict;
import com.mck.activiti.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@RestController
public class IndexController {

    @Autowired
    private ISystemService systemService;

    @RequestMapping("queryMenu")
    public Map<String, Object> queryMenu() {
        Map<String, Object> map = systemService.queryMenuList();
        return map;
    }

    @RequestMapping("querySysDict")
    public ResponseResult<List<SysDict>> querySysDict() {
        List<SysDict> sysDicts = systemService.querySysDictInfo(SysConstant.SYSTEM_CODE);
        return ResponseUtil.makeOKRsp(sysDicts);
    }
}

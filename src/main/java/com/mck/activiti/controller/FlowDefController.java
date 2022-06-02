package com.mck.activiti.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.config.BizException;
import com.mck.activiti.common.entity.*;
import com.mck.activiti.model.entity.FlowDef;
import com.mck.activiti.model.entity.FlowMain;
import com.mck.activiti.model.entity.FlowRule;
import com.mck.activiti.model.entity.SysDict;
import com.mck.activiti.service.IFlowInfoService;
import com.mck.activiti.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Controller
@RequestMapping("flow")
public class FlowDefController {

    @Autowired
    private ISystemService systemService;
    @Autowired
    private IFlowInfoService flowInfoService;

    /**
     * 查询流程规则
     *
     * @return
     */
    @RequestMapping("queryFlowRule")
    @ResponseBody
    public ResponseTableResult<List<FlowRule>> queryFlowRule(PageBean pageBean) {
        Page<FlowRule> flowRulePage = flowInfoService.queryFlowRule(pageBean);
        return ResponseUtil.makeTableRsp(0, flowRulePage.getTotal(), flowRulePage.getRecords());
    }

    /**
     * 跳转到新增规则页面
     *
     * @param model
     * @return
     */
    @GetMapping("addFlowRule")
    public String addFlowRule(Model model) {
        List<SysDict> systemList = systemService.querySysDictInfo(SysConstant.SYSTEM_CODE);
        List<SysDict> busitypeList = systemService.querySysDictInfo(SysConstant.BUSI_TYPE);
        List<FlowDef> flowDefList = flowInfoService.queryFlowDefList();
        model.addAttribute("systemList", systemList);
        model.addAttribute("busitypeList", busitypeList);
        model.addAttribute("flowDefList", flowDefList);
        return "page/addFlowRule";
    }


    @PostMapping("submitFlowRule")
    @ResponseBody
    public ResponseResult<String> submitFlowRule(@RequestBody FlowRule flowRule) {
        String resMsg = flowInfoService.insertFlowRule(flowRule);
        if (StrUtil.isNotBlank(resMsg)) {
            throw new BizException(ResultCode.FAIL.code, resMsg);
        }
        return ResponseUtil.makeOKRsp(resMsg);
    }

    /**
     * 删除流程
     *
     * @param request
     * @return
     */
    @GetMapping("delFlow")
    @ResponseBody
    public ResponseResult<String> delModel(HttpServletRequest request) {
        String ruleId = request.getParameter("ruleId");
        if (StrUtil.isBlank(ruleId)) {
            throw new BizException(ResultCode.NOT_FOUND.code, "流程规则ID不存在!", "流程规则ID不存在!");
        }
        List<FlowMain> flowMains = flowInfoService.queryFlowByRuleId(ruleId);
        if (ObjectUtil.isNotEmpty(flowMains)) {
            throw new BizException(ResultCode.INTERNAL_SERVER_ERROR.code, "改流程已被使用，无法删除!", "改流程已被使用，无法删除!");
        }
        flowInfoService.deleteFlowRuleById(ruleId);
        return ResponseUtil.makeOKRsp("删除流程成功");
    }
}

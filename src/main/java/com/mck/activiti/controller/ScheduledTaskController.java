package com.mck.activiti.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseTableResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.model.entity.ScheduledTask;
import com.mck.activiti.service.IScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Controller
@RequestMapping("scheduled")
public class ScheduledTaskController {

    @Autowired
    private IScheduledTaskService scheduledTaskService;

    /**
     * 列表分页查询
     *
     * @param pageBean
     * @return
     */
    @GetMapping("queryList")
    @ResponseBody
    public ResponseTableResult<List<ScheduledTask>> queryList(PageBean pageBean) {
        Page<ScheduledTask> scheduledTaskPage = scheduledTaskService.queryList(pageBean);
        return ResponseUtil.makeTableRsp(0, scheduledTaskPage.getTotal(), scheduledTaskPage.getRecords());
    }

    /**
     * 新增任务呀
     *
     * @param scheduledTask
     * @return
     */
    @PostMapping("addTask")
    @ResponseBody
    public ResponseResult<String> addTask(@RequestBody ScheduledTask scheduledTask) {
        scheduledTaskService.addTask(scheduledTask);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 编辑任务
     *
     * @param taskId
     * @return
     */
    @GetMapping("toEdit")
    public String toEdit(@RequestParam("taskId") String taskId, Model model) {
        ScheduledTask taskVO = scheduledTaskService.queryScheduled(taskId);
        model.addAttribute("taskVO", taskVO);
        return "page/editScheduled";
    }

    /**
     * 修改任务
     *
     * @param scheduledTask
     * @return
     */
    @PostMapping("updateTask")
    @ResponseBody
    public ResponseResult<String> updateTask(@RequestBody ScheduledTask scheduledTask) {
        scheduledTaskService.updateTask(scheduledTask);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 修改状态
     *
     * @param scheduledTask
     * @return
     */
    @PostMapping("updateState")
    @ResponseBody
    public ResponseResult<String> updateState(@RequestBody ScheduledTask scheduledTask) {
        scheduledTaskService.updateState(scheduledTask);
        return ResponseUtil.makeOKRsp();
    }


    /**
     * 删除任务
     *
     * @param taskId
     * @return
     */
    @PostMapping("delTask")
    @ResponseBody
    public ResponseResult<String> delTask(@RequestParam("taskId") String taskId) {
        scheduledTaskService.delTask(taskId);
        return ResponseUtil.makeOKRsp();
    }
}

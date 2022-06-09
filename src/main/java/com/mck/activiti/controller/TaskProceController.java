package com.mck.activiti.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mck.activiti.common.entity.PageBean;
import com.mck.activiti.common.entity.ResponseResult;
import com.mck.activiti.common.entity.ResponseTableResult;
import com.mck.activiti.common.entity.ResponseUtil;
import com.mck.activiti.module.flow.model.vo.TaskVo;
import com.mck.activiti.module.flow.service.ITaskProceService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping("task")
public class TaskProceController {


    @Autowired
    private ITaskProceService taskProceService;

    /**
     * 查询我的任务
     *
     * @param pageBean
     * @return
     */
    @RequestMapping("queryMyTask")
    @ResponseBody
    public ResponseTableResult<List<TaskVo>> queryMyTask(PageBean pageBean) {
        Page<TaskVo> taskPage = taskProceService.queryMyTask(pageBean);
        return ResponseUtil.makeTableRsp(0, taskPage.getTotal(), taskPage.getRecords());
    }

    /**
     * 跳转流程审批
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("toTaskExec")
    public String toTaskExec(Model model, HttpServletRequest request) {
        String vacationId = request.getParameter("vacationId");
        TaskVo taskInfo = taskProceService.queryTaskById(Long.valueOf(vacationId));
        model.addAttribute("taskInfo", taskInfo);
        return "/page/taskExec";
    }

    /**
     * 流程审批
     *
     * @param taskVo
     * @return
     */
    @PostMapping("completeTask")
    @ResponseBody
    public ResponseResult<String> completeTask(@RequestBody TaskVo taskVo) {
        taskProceService.completeTask(taskVo);
        return ResponseUtil.makeOKRsp();
    }
}

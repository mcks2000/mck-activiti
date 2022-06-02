package com.mck.activiti.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Description: 页面跳转
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Controller
public class PageController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String toIndexPage() {
        return "index";
    }

    /**
     * @return java.lang.String
     * @Author mck
     * @Description 跟路径跳转
     * @Date 2019/7/2 22:20
     * @Param [page]
     **/
    @GetMapping("/{page}")
    public String toPage(@PathVariable String page) {
        if (StrUtil.equals("favicon.ico", page)) {
            return "favicon";
        }
        return page;
    }


    /**
     * 跳转流程相关页面
     *
     * @param page
     * @return
     */
    @GetMapping("/model/{page}")
    public String toModelPage(@PathVariable String page) {
        return "model" + File.separator + page;
    }

    /**
     * 跳转其他相关页面
     *
     * @param page
     * @return
     */
    @GetMapping("/page/{page}")
    public String toOtherPage(@PathVariable String page) {
        return "page" + File.separator + page;
    }

}

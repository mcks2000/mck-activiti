package com.mck.activiti.common.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 测试定时任务
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
@Component("testMckTask")
public class MckTask {

    public void taskWithParams(String params) {
        log.info("------->执行带参数任务:{}", params);
    }

    public void taskNoParams() {
        log.info("---------->执行无参数任务");
    }
}

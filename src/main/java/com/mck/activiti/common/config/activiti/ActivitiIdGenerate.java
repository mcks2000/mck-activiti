package com.mck.activiti.common.config.activiti;

import cn.hutool.core.util.IdUtil;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * @Description: activiti自定义ID生成策略
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Component
public class ActivitiIdGenerate implements IdGenerator {

    /**
     * @return
     * @deprecated 雪花算法，生成ID
     */
    @Override
    public String getNextId() {
        return String.valueOf(IdUtil.getSnowflake(0, 0).nextId());
    }
}

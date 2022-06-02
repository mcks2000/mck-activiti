package com.mck.activiti.service;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * @Description: 流程服务接口
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
public interface IProcesService {

    /**
     * 启动流程
     *
     * @param processDefinitionKey 流程定义KEY
     * @param businessKey          业务编码
     * @return
     */
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

}

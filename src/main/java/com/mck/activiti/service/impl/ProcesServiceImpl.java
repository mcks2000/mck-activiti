package com.mck.activiti.service.impl;

import com.mck.activiti.service.IProcesService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Service
@Slf4j
public class ProcesServiceImpl implements IProcesService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
    }
}

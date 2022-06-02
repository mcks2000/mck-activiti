package com.mck.activiti.listener;

import com.mck.activiti.common.util.SpringUtils;
import com.mck.activiti.model.entity.User;
import com.mck.activiti.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @Description: 任务审批监听
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Slf4j
public class TaskApprovListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("creattime: {}", delegateTask.getCreateTime());

        log.info("getProcessInstanceId: {}", delegateTask.getProcessInstanceId());


        log.info("数据库中的taskId主键: {}", delegateTask.getId());
        log.info("任务名称: {}", delegateTask.getName());
        delegateTask.setName("修改任务名称");

        log.info("获取任务的描述信息: {}", delegateTask.getDescription());
        delegateTask.setDescription("修改任务的描述信息");
        /**
         * lower priority: [0..19] lowest, [20..39] low, [40..59] normal, [60..79] high
         * [80..100] highest
         任务处理的优先级范围是0-100
         */
        log.info("任务处理的优先级范围是0-100: {}", delegateTask.getPriority());
        delegateTask.setPriority(1); /** 修改优先级*/

        log.info("获取流程实例id: {}", delegateTask.getProcessInstanceId());
        log.info("获取流程获取执行id: {}", delegateTask.getExecutionId());
        log.info("获取流程定义id: {}", delegateTask.getProcessDefinitionId());
        /** 添一个加候选人 */
        //void addCandidateUser(String userId);

        /** 添加候选人集合 */
        //void addCandidateUsers(Collection<String> candidateUsers);

        /** 添加候选组 */
        //void addCandidateGroup(String groupId);


        String eventName = delegateTask.getEventName();
        if (EVENTNAME_CREATE.endsWith(eventName)) {
            System.out.println("create=========");
        } else if (EVENTNAME_ASSIGNMENT.endsWith(eventName)) {
            System.out.println("assignment========");
        } else if (EVENTNAME_COMPLETE.endsWith(eventName)) {
            System.out.println("complete===========");
        } else if (EVENTNAME_DELETE.endsWith(eventName)) {
            System.out.println("delete=============");
        }


        IUserService userService = SpringUtils.getBean(IUserService.class);
        User currentUser = userService.getCurrentUser();
        delegateTask.setAssignee(currentUser.getParentUserId());
        log.info("执行审批任务监听器ID:{},办理人:{}", delegateTask.getId(), delegateTask.getAssignee());
    }
}

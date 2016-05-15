package com.github.jwxa.service.impl;

import com.github.jwxa.service.IWorkflowService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 工作流Service
 * Created by Jwxa on 2015/2/9.
 */
@Service
public class WorkflowServiceImpl implements IWorkflowService {
    @SuppressWarnings("unused")
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;
    @Resource
    private ManagementService managementService;
    @Resource
    private ProcessEngine processEngine;


    @Override
    public void deploymentProcessDefinition(String path,String processName) {
        repositoryService.createDeployment()//创建一个部署对象
                .name(processName)
                .addClasspathResource(path)
                .deploy();//完成部署
    }

    @Override
    public ProcessInstance startProcessInstance(String processDefinitionKey,Map<String,Object> variables) {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey, variables);//对应bpmn文件中的id属性
        return  processInstance;
    }

    @Override
    public List<Task> findMyPersonalTask(String assignee) {
        List<Task> list =  taskService.createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        return list;
    }

    @Override
    public boolean completeMyPersonalTask(String taskId, Map<String, Object> variables) {
        boolean flag = false;
        //流程定义的key
        try {
            taskService.complete(taskId,variables);
            flag = true;
        } catch (Exception e) {
//            log.error(e.getMessage(),e);
        }
        return  flag;
    }

}

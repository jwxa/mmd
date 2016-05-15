package com.github.jwxa.service;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * 工作流Service接口
 * Created by Jwxa on 2015/2/9.
 */
public interface IWorkflowService {
    /**
     * 部署流程定义
     * @param  path 路径
     * @param  processName 流程命名
     */
    public void deploymentProcessDefinition(String path, String processName);

    /**
     * 启动流程实例
     * @param  processDefinitionKey "introduceProcess" bpmn文件的id
     * @param  variables 参数map 放入userID 用户id
     * @return 流程实例
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, Map<String, Object> variables);

    /**
     * 查询当前人的个人任务
     * @param assignee 指定办理人(userID)
     * @return 任务List
     */
    public List<Task> findMyPersonalTask(String assignee);

    /**
     * 通过任务ID完成任务
     * @param taskId 任务ID
     * @param variables 参数map 用于存放结果信息 为了指向不同分支
     * @return 操作结果
     */
    public boolean completeMyPersonalTask(String taskId, Map<String, Object> variables);

}

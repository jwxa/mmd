package com.github.jwxa.controller;

import com.github.jwxa.service.IStatusService;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 任务进度请求测试Controller<BR>
 * User: Jwxa<BR>
 * Date: 2015/11/5  Time: 18:33<BR>
 */
@Controller
@Scope("prototype")
public class StatusController {
    @Resource
    private IStatusService statusService;

    /**
     * 模拟查询状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryStatus")
    public Map queryStatus(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("status",statusService.getTaskStatus());
        return map;
    }

    /**
     * 模拟开始任务
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/startTask")
    public String  startTask() throws InterruptedException {
        statusService.startTask();
        return "OK";
    }

    /**
     * 模拟下载请求
     */




}

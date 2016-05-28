package com.github.jwxa.controller;

import com.github.jwxa.interceptor.annotation.SystemControllerLog;
import com.github.jwxa.service.IIqqService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/25.
 */
@RequestMapping("/iqq")
@Controller
@Slf4j
public class IqqController {
    @Resource
    private IIqqService iqqService;

    @SystemControllerLog(description = "访问iqq配置管理页面")
    @RequestMapping("/showManagePage")
    public String showIqqPage(){
        return "/iqq/iqqPage";
    }

    @SystemControllerLog(description = "获取iqq登陆二维码")
    @RequestMapping("/getQRCode")
    public void getQRCode(HttpServletResponse response){
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        BufferedImage bufferedImage = iqqService.getQRCode();
        try {
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @SystemControllerLog(description = "检查二维码状态")
    @RequestMapping("/checkQRCode")
    @ResponseBody
    public Map<String,Object> checkQRCode(){
        Map map = Maps.newHashMap();
        String result =  iqqService.checkQRCode();
        map.put("resultStr",result);
        return map;
    }


    @RequestMapping("/beginPollMsg")
    @ResponseBody
    public Map<String,Object> beginPollMsg(){
        Map<String,Object> map = Maps.newHashMap();
        boolean flag = false;
        try {
            iqqService.beginPollMsg();
            flag = true;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        map.put("flag",flag);
        return map;
    }

//    @RequestMapping("/sendQQMsg.do")
//    @ResponseBody
//    public Map<String,Object>  sendQQMsg(){
//
//    }




}

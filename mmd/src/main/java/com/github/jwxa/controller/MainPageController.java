package com.github.jwxa.controller;

import com.github.jwxa.util.CommonConstant;
import com.github.jwxa.interceptor.annotation.SystemControllerLog;
import com.github.jwxa.model.UserInfo;
import com.github.jwxa.service.IMainPageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页面和菜单Conntroller层
 * Created by Jwxa on 2015/2/7.
 */
@Controller
@Slf4j
public class MainPageController {
//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MainPageController.class);
    @Resource
    private IMainPageService mainPageService;

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String showLoginPage(){
        return "mainpage/login";
    }

    /**
     * 首页面
     * @return
     */
    @SystemControllerLog(description = "进入首页面")
    @RequestMapping("/index")
    public String showMainPage(){
        return "mainpage/index";
    }

    /**
     * 菜单加载
     * @return
     */
    @RequestMapping("/menu")
    public String showMenu(){
        return "mainpage/menu";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping("/toRegister")
    public String showRegisterPage(){
        return "mainpage/register";
    }

    /**
     * 登录方法（验证用户名密码）
     */
    @RequestMapping("/login")
    public String login(UserInfo inputUserInfo,HttpServletRequest request,HttpSession session){
        if(inputUserInfo==null){
            log.info("用户信息参数获取失败");
            return "redirect:/toLogin";
        }
        if(StringUtils.isBlank(inputUserInfo.getLoginName())||StringUtils.isBlank(inputUserInfo.getLoginPwd())){
            log.info("用户名或密码不能为空");
            request.setAttribute("err_msg", "用户名或密码不能为空");
            request.setAttribute("login_name",inputUserInfo.getLoginName());
            request.setAttribute("login_pwd",inputUserInfo.getLoginPwd());
            return "forward:mainpage/login.jsp";
        }
        //验证用户名和密码是否存在于数据库中
        UserInfo userInfo = mainPageService.verifyUserInfo(inputUserInfo);
        if (userInfo==null){
            log.info("验证失败");
            request.setAttribute("err_msg", "验证失败,请重新检查用户名和密码");
            request.setAttribute("login_name",inputUserInfo.getLoginName());
            request.setAttribute("login_pwd",inputUserInfo.getLoginPwd());
            return "forward:mainpage/login.jsp";
        }else{
            //将账户信息存入session
           session.setAttribute(CommonConstant.USER_INFO,userInfo);
            //跳转到首页面
            return "redirect:/index";
        }
    }

    /**
     * 登出/注销方法
     */
    @SystemControllerLog(description = "登出/注销方法")
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user_info");
        return "redirect:/toLogin";
    }

    /**
     * 欢迎页面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "mainpage/welcome";
    }
}

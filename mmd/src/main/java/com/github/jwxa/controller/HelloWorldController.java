package com.github.jwxa.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Freemarker测试类
 */
@Api(value = "HelloWorld", description = "HelloWorld",position = 5)
@Controller
public class HelloWorldController {

    @ApiOperation(value = "freemarker测试方法", notes = "freemarker测试方法", position = 3)
    @RequestMapping("hello")
    public ModelAndView handleRequest(HttpServletRequest hsr
            , HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("freemarker/hello");
        mv.addObject("title", "Spring MVC And Freemarker");
        mv.addObject("content", " Hello world ， test my first spring mvc ! ");
        return mv;
    }
}

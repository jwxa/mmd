package com.github.jwxa.controller;

import com.github.jwxa.model.SpringRestVO;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST方式开发的DEMO
 * 注解本身使用@Controller和@ResponseBody注解。使用了这个注解的类会被看作一个controller-使用@RequestMapping的方法有一个默认的@ResponseBody注解。
 * Created by Jwxa on 2015/8/5.
 */
@RequestMapping("/api")
@RestController
public class SpringRestController {
    @ApiOperation(value = "REST方式开发测试方法", notes = "REST方式开发测试方法", position = 3)
    @RequestMapping(value="/springcontent",method= RequestMethod.GET,produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SpringRestVO getSpringRestVO(){
        SpringRestVO springRestVO = new SpringRestVO();
        springRestVO.setUserName("jwxa");
        springRestVO.setEmailId("jwxa9000@qq.com");
        return springRestVO;
    }

    @RequestMapping(value="/springcontent.htm", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserHtml() {
        return "example";
    }
}

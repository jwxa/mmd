package com.github.jwxa.interceptor;

/**
 * Created by Jwxa on 2015/2/8.
 */
import com.github.jwxa.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * 登录拦截器切面类
 */
@Aspect
@Component
@Slf4j
public class BasedInterceptorAspect implements Ordered {


    /**
     * Controller层切点
     * 用于重定向未登录情况
     */
    @Pointcut("execution(* com.github.jwxa.controller.*.*(..))")
    public  void loginAspect() {
    }

    @Around("loginAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 这里相当于@Before
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String path =request.getContextPath();
        String requestURI = request.getRequestURI().replace(path, "");
        List<String> urlList = Arrays.asList(CommonConstant.NO_INTERCEPTOR_URL);
        if(!urlList.contains(requestURI) && session.getAttribute("user_info") == null){
            Signature signature =  point.getSignature();
            Class returnType = ((MethodSignature) signature).getReturnType();
            if(returnType.getName().contains("ModelAndView")){
                ModelAndView mv = new ModelAndView("mainpage/login");
                return mv;
            }else{
                return "redirect:/toLogin";
            }
        }else{
            //调用原方法
            Object obj = point.proceed();// 调用方法具体执行过程，如果不调用，这原来的方法就不会执行了
            // obj为原来的方法返回值，如果不返回obj，则原来的方法即时有return也不会返回任何值
            return obj;
        }
        // 这里相当于@After
    }


    @Override
    public int getOrder() {
        return 0;
    }
}


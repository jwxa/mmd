package com.github.jwxa.interceptor;

import com.github.jwxa.util.CommonConstant;
import com.github.jwxa.interceptor.annotation.SystemControllerLog;
import com.github.jwxa.model.UserInfo;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 切面类
 * 通过AOP将访问Controller页面的方法中获取ip信息的代码提取至此
 * Created by Jwxa on 2015/7/5.
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect implements Ordered {
    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.github.jwxa.interceptor.annotation.SystemServiceLog)")
    public void serviceAspect(){}

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.github.jwxa.interceptor.annotation.SystemControllerLog)")
    public  void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //请求的IP
        String ip = request.getRemoteAddr();
        UserInfo userInfo = (UserInfo) session.getAttribute(CommonConstant.USER_INFO);
        userInfo = Optional.fromNullable(userInfo).or(new UserInfo());
        try {
            //日志格式[用户账号][IP][执行方法][方法描述]
            log.info("用户名:[{}],IP:[{}],请求方法:[{}],描述:[{}]",userInfo.getLoginName(),ip,(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"),getControllerMethodDescription(joinPoint));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

package com.github.jwxa.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截service
 * Created by Jwxa on 2015/7/5.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description()  default "";
}

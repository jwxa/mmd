package com.github.jwxa.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户信息实体类
 * Created by Jwxa on 2015/2/8.
 */
@Getter
@Setter
public class UserInfo implements Serializable{
    /**
     * 序号
     */
    private int id;
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 密码
     */
    private String  loginPwd;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 性别
     */
    private String userGender;
    /**
     * 年龄
     */
    private int userAge;
    /**
     * 邮箱
     */
    private String emailAddress;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 是否注销
     */
    private String isDelete;


}

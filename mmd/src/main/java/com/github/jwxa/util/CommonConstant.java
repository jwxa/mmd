package com.github.jwxa.util;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * <BR>
 * User: Jwxa<BR>
 * Date: 2015/2/17  Time: 20:27<BR>
 */
public class CommonConstant {

    public static  final String[] NO_INTERCEPTOR_URL = new String[]{
            "/toLogin","/toRegister","/login","mainpage/login.jsp"
    };
    /**
     * 软件简介状态码
     * 001000 未审核 001001 审核通过 001002 审核未通过
     */
    public static final String[] ARTICLE_STATUS_CODE =new String[]{"001000","001001","001002"};
    /**
     * 从session中获取用户信息用字符串
     */
    public static final String USER_INFO = "user_info";
    /**
     * 假名表multimap
     */
    public static final String ALPHABET_MULTIMAP= "alphabet_multimap";
    /**
     * 假名类型
     * 平假名 片假名 全部
     */
    public static final String[] ALPHABET_ALIAS_KIND = new String []{"002001","002002","002003","002004"};
    public static final HashMap<String,String> ALPHABET_ALIAS_KIND_MAP =Maps.newHashMap();
    static {
        ALPHABET_ALIAS_KIND_MAP.put(ALPHABET_ALIAS_KIND[0],"平假名");
        ALPHABET_ALIAS_KIND_MAP.put(ALPHABET_ALIAS_KIND[1],"片假名");
        ALPHABET_ALIAS_KIND_MAP.put(ALPHABET_ALIAS_KIND[2],"罗马音");
        ALPHABET_ALIAS_KIND_MAP.put(ALPHABET_ALIAS_KIND[3],"全部假名");
    }

    /**
     * 读音练习  打乱范围
     * 按行   按列   全表
     */
    public static final String[] PRONUNCIATION_EXAM_CREATE_KIND = new String[]{
            "003001","003002","003003"
    };

    /**
     * 读音练习 表格显示
     *  平假名   片假名   平假名&片假名   发音
     */
    public static  String[] PRONUNCIATION_EXAM_SHOW_KIND = new String[]{
            "004001","004002","004003","004004"
    };
}

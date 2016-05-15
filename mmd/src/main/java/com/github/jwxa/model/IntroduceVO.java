package com.github.jwxa.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 简介实体类
 * Created by Jwxa on 2015/2/8.
 */
@Getter
@Setter
public class IntroduceVO implements Serializable{
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String body;
    /**
     * 作者名
     */
    private String author;
    /**
     * 作者id
     */
    private int authorId;
    /**
     * 状态码
     */
    private String status;
    /**
     * 状态名
     */
    private String statusName;
    /**
     * 唯一标示符
     */
    private String uuid;

    /**
     * 创建时间
     */
    private String createTime;



}

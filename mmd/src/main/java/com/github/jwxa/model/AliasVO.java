package com.github.jwxa.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 假名实体类
 */
@Getter
@Setter
public class AliasVO implements Serializable{

    /**
     * 假名类型
     */
    private String kind;

    /**
     * 假名
     */
    private String aliasName;

    /**
     * 发音
     */
    private String pronounce;

    /**
     * 声音来源
     */
    private String soundSrc;

    /**
     * 行列数
     */
    private String ranksNum;


}
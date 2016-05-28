package com.github.jwxa.model.tulin;

import lombok.Getter;
import lombok.Setter;

/**
 * 新闻详情实体类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class NewsDetailRespVO {

    /**
     * 标题
     */
    private String article;
    /**
     * 来源
     */
    private String source;

    /**
     * 图标
     */
    private String icon;

    /**
     * 详细地址url
     */
    private String detailurl;
}

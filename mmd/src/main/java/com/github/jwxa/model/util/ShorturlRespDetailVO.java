package com.github.jwxa.model.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class ShorturlRespDetailVO {

    private String type;

    private List<UrlRespVO> urls;
}

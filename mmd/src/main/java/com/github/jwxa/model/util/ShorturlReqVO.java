package com.github.jwxa.model.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 短网址请求实体类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class ShorturlReqVO {

    private String type;

    private List<String> url;

}

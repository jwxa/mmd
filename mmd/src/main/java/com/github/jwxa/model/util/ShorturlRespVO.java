package com.github.jwxa.model.util;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * 短网址响应实体类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class ShorturlRespVO {

    private ShorturlRespDetailVO  data;

    private String error;

    private String msg;


}

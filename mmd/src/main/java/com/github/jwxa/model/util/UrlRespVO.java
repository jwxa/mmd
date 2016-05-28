package com.github.jwxa.model.util;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class UrlRespVO {

    @SerializedName("short_url")
    private String shortUrl;

    @SerializedName("src_url")
    private String srcUrl;
}

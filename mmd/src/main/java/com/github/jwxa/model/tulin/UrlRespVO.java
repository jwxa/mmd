package com.github.jwxa.model.tulin;

import lombok.Getter;
import lombok.Setter;

/**
 * 连接类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class UrlRespVO  extends TulinRespVO {
    private String url;

    @Override
    public String showText() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.getText()).append("\n").append("链接：").append(url);
        return sb.toString();
    }
}

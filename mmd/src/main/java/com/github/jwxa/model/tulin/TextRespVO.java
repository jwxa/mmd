package com.github.jwxa.model.tulin;

import lombok.Getter;
import lombok.Setter;

/**
 * 新闻类响应实体类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class TextRespVO extends TulinRespVO {

    @Override
    public String toString() {
        return super.getText();
    }
}

package com.github.jwxa.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页实体类
 * Created by Jwxa on 2015/6/25.
 */
@Getter
@Setter
public class PageVO {
    private Integer iDisplayStart;

    private Integer iDisplayLength;

    public PageVO(Integer iDisplayStart, Integer iDisplayLength) {
        this.iDisplayStart = iDisplayStart;
        this.iDisplayLength = iDisplayLength;
    }

    public PageVO() {

    }

}

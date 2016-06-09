package com.github.jwxa.model.study;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 测试请求实体类<br>
 * User: Jwxa<br>
 * Date: 2016/6/4.
 */
@Getter
@Setter
public class ExamReqVO implements Serializable {
    /**
     * 五十音图中字段类型
     */
    private String aliasType;

    /**
     * 测试所对应的模式选择向
     */
    private String createType;

}

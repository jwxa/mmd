package com.github.jwxa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 洛英物品信息
 * Created by Jwxa on 2015/7/19.
 */
@AllArgsConstructor
@Getter
@Setter
public class ItemVO {
    /**
     * 变量名
     */
    private String paramName;
    /**
     * 显示名
     */
    private String itemName;
    /**
     * 物品描述
     */
    private String itemDesc;

    public ItemVO() {
    }
}

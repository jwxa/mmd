package com.github.jwxa.util;

/**
 * 数据源枚举类
 * Created by Jwxa on 2015/6/22.
 */
public enum DataSource {
    MMD("mmd")
    ;
    private String name;

    DataSource(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}

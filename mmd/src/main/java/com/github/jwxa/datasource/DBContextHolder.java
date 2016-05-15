package com.github.jwxa.datasource;

/**
 * 需要在Service层切换数据源
 * Created by Jwxa on 2015/1/11.
 */
public class DBContextHolder{

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDBType() {
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}


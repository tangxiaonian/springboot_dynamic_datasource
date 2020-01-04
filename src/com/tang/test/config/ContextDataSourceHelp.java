package com.tang.test.config;

/**
 * @Classname ContextDataSourceHelp
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/1/4 16:55
 * @Created by ASUS
 */
public class ContextDataSourceHelp {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getDbType() {
        return threadLocal.get();
    }

    public static void setDbType(String dbType) {
        threadLocal.set(dbType);
    }

    public void clearDbType() {
        threadLocal.remove();
    }
}
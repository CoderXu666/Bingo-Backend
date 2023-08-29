package com.bingo.utils;

import org.apache.coyote.RequestInfo;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-29  14:53
 * @Description: 请求全局上下文
 * @Version: 1.0
 */
public class RequestHolderUtil {
    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    /**
     * 保存设置信息
     */
    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    /**
     * 获取信息
     */
    public static RequestInfo get() {
        return threadLocal.get();
    }

    /**
     * 移除信息
     */
    public static void remove() {
        threadLocal.remove();
    }
}

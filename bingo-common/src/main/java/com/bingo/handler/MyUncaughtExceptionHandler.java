package com.bingo.handler;


import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-29  15:01
 * @Description: 未捕捉异常处理器
 * @Version: 1.0
 * -------------------------------------------------------------
 * 作用：仅仅为了打印日志，别指望把异常抛出给全局异常处理器处理
 */
@Slf4j
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final MyUncaughtExceptionHandler instance = new MyUncaughtExceptionHandler();

    public static MyUncaughtExceptionHandler getInstance() {
        return instance;
    }

    /**
     * 捕捉异常（线程池）
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("线程池中线程运行出现了异常Exception {} ", t.getName(), e);
    }
}
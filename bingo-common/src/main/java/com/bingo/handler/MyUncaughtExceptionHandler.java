package com.bingo.handler;


import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-29  15:01
 * @Description: 线程池异常处理器
 * @Version: 1.0
 */
@Slf4j
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final MyUncaughtExceptionHandler instance = new MyUncaughtExceptionHandler();

    public static MyUncaughtExceptionHandler getInstance() {
        return instance;
    }

    /**
     * 作用：捕捉异常（线程池），打印日志
     * 别指望把异常抛出给全局异常处理器处理，异步操作出现的异常没啥可处理的
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("=========================线程池中线程运行出现了异常Exception {}=========================", t.getName(), e);
    }
}
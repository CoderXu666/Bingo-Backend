//package com.bingo.handler;
//
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @Author: 徐志斌
// * @CreateTime: 2023-08-29  15:01
// * @Description: 未捕捉异常处理器
// * @Version: 1.0
// */
//@Slf4j
//public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
//    private static final MyUncaughtExceptionHandler instance = new MyUncaughtExceptionHandler();
//
//    public static MyUncaughtExceptionHandler getInstance() {
//        return instance;
//    }
//
//    /**
//     * 捕捉异常（线程池）
//     */
//    @Override
//    public void uncaughtException(Thread t, Throwable e) {
//        log.error("Exception in thread {} ", t.getName(), e);
//    }
//}
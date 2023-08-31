//package com.bingo.factory;
//
//import com.bingo.handler.MyUncaughtExceptionHandler;
//import lombok.AllArgsConstructor;
//
//import java.util.concurrent.ThreadFactory;
//
///**
// * @Author: 徐志斌
// * @CreateTime: 2023-08-29  16:38
// * @Description: 自定义线程工厂（用于线程池参数）
// * @Version: 1.0
// */
//@AllArgsConstructor
//public class MyThreadFactory implements ThreadFactory {
//    private final ThreadFactory factory;
//
//    /**
//     * TODO: 这个线程异常可以测测能不能捕捉到
//     * 线程工厂创建出来的每个线程都设置：未捕获异常处理器
//     * 线程中发现未捕获异常及时处理
//     */
//    @Override
//    public Thread newThread(Runnable r) {
//        Thread thread = factory.newThread(r);
//        thread.setUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());
//        return thread;
//    }
//}

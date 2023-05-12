package com.bingo.config;

import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author 徐志斌
 * @Date: 2023/5/12 21:55
 * @Version 1.0
 * @Description: @Async异步配置类
 */
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(4);
        threadPool.setQueueCapacity(100);
        threadPool.setMaxPoolSize(1);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setAwaitTerminationSeconds(60 * 15);
        threadPool.setThreadNamePrefix("MyAsync-");
        threadPool.initialize();
        return threadPool;
    }
}

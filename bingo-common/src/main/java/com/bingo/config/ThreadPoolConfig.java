package com.bingo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-29  13:43
 * --------------------------------------------------------------------------------
 * 区别：
 * 1.ThreadPoolExecutor：Java标准库中的线程池类
 * 2.ThreadPoolTaskExecutor：对ThreadPoolExecutor进行了封装处理，具备更多功能
 * -------------------------------------------------------------------------------
 * 拒绝策略：
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
 */
@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolConfig {
    private static final String CHAT_POOL_NAME = "CHAT_POOL_EXECUTOR";
    private static final int coreSize = 10;
    private static final int maxSize = 20;
    private static final int queueCapacity = 200;
    private static final int aliveSecond = 60;
    private static final String prefixName = "chat-executor-pool-thread-";

    /**
     * 聊天通讯专用线程吃池
     */
    @Bean(CHAT_POOL_NAME)
    public ThreadPoolTaskExecutor chatExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(aliveSecond);
        executor.setThreadNamePrefix(prefixName);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}

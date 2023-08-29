package com.bingo.config;

import com.bingo.factory.MyThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    private static final int CORE_SIZE = 10;
    private static final int MAX_SIZE = 20;
    private static final int QUEUE_CAPACITY = 200;
    private static final int ALIVE_SECOND = 60;
    private static final String PREFIX_NAME = "chat-executor-pool-thread-";

    /**
     * 聊天通讯专用——线程池
     */
    @Primary
    @Bean(CHAT_POOL_NAME)
    public ThreadPoolTaskExecutor chatExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_SIZE);
        executor.setMaxPoolSize(MAX_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(ALIVE_SECOND);
        executor.setThreadNamePrefix(PREFIX_NAME);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadFactory(new MyThreadFactory(executor));
        executor.initialize();
        return executor;
    }
}

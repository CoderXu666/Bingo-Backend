package com.bingo.threadpool;

import com.bingo.threadpool.MyThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
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
 * 为什么通过该方式使用线程池：
 * 1.线程池交给Spring容器统一管理，不需要频繁的创建
 * 2.不需要手动释放线程池资源，当项目关闭的时候，需要通过JVM的shutdownHook回调线程池，等队列里任务执行完再停机，保证任务不丢失。
 * 3.不使用@Async注解进行异步，坑太多了！！！
 */
@Slf4j
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    public static final String CHAT_POOL_NAME = "CHAT_POOL_EXECUTOR";

    /**
     * 聊天通讯专用——线程池
     */
    @Primary
    @Bean(CHAT_POOL_NAME)
    public ThreadPoolTaskExecutor chatExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("chat-pool-thread-");
        executor.setWaitForTasksToCompleteOnShutdown(true); // 优雅释放线程池
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadFactory(new MyThreadFactory(executor));
        executor.initialize();
        return executor;
    }
}

package com.bingo.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author 徐志斌
 * @Date: 2023/5/11 22:15
 * @Version 1.0
 * @Description: Redisson分布式锁
 */
@Slf4j
@Component
public class DistributedLock {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁
     */
    public Boolean lock(String lockKey) {
        redissonClient.getLock(lockKey).lock(15, TimeUnit.SECONDS);
        log.info("------------------------Thread [{}] DistributedLock lock [{}] success--------------------------", Thread.currentThread().getName(), lockKey);
        return true;
    }

    /**
     * 释放锁
     */
    public Boolean unlock(String lockKey) {
        redissonClient.getLock(lockKey).unlock();
        log.info("------------------------Thread [{}] DistributedLock unlock [{}] success------------------------", Thread.currentThread().getName(), lockKey);
        return true;
    }
}

package com.bingo.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author 徐志斌
 * @Date: 2023/5/11 22:15
 * @Version 1.0
 * @Description: DistributedRedisLock
 */
@Slf4j
@Component
public class DistributedRedisLock {
    @Autowired
    private RedissonClient redissonClient;

    // 加锁
    public Boolean lock(String lockName) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
            return false;
        }
        try {
            RLock lock = redissonClient.getLock(lockName);
            lock.lock(15, TimeUnit.SECONDS);
            log.info("Thread [{}] DistributedRedisLock lock [{}] success", Thread.currentThread().getName(), lockName);
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock lock [{}] Exception:", lockName, e);
            return false;
        }
    }

    // 释放锁
    public Boolean unlock(String lockName) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
            return false;
        }
        try {
            RLock lock = redissonClient.getLock(lockName);
            lock.unlock();
            log.info("Thread [{}] DistributedRedisLock unlock [{}] success", Thread.currentThread().getName(), lockName);
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock unlock [{}] Exception:", lockName, e);
            return false;
        }
    }
}

package com.bingo.annotation;

import com.bingo.enums.LimitTypeEnum;

import java.lang.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-30  21:07
 * @Description: Redis限流注解
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default "rate_limit:";

    /**
     * 限流时间（秒）
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitTypeEnum limitType() default LimitTypeEnum.DEFAULT;
}

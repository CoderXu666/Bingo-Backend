package com.bingo.annotation;


import com.bingo.enums.CacheEnum;

import java.lang.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-06-15  21:07
 * @Description: 自定义的SpringCache注解：二级缓存的操作
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoubleCache {
    /**
     * 缓存分区名
     */
    String cacheName();

    /**
     * 缓存的key
     */
    String key();

    /**
     * 缓存过期时间
     */
    long l2TimeOut() default 120;

    /**
     * 操作类型：具体参考CacheType
     */
    CacheEnum type() default CacheEnum.FULL;
}

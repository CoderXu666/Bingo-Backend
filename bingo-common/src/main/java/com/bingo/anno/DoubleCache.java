package com.bingo.anno;


import com.bingo.enums.CacheType;

import java.lang.annotation.*;

/**
 * @Author 徐志斌
 * 自定义的SpringCache注解：可以统一完成二级缓存的操作
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
    CacheType type() default CacheType.FULL;
}

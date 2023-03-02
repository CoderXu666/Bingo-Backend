package com.bingo.anno;


import com.bingo.enums.CacheType;

import java.lang.annotation.*;

/**
 * 自定义的SpringCache注解
 * 可以统一完成二级缓存的操作
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoubleCache {
    String cacheName();

    String key();    //支持springEl表达式

    long l2TimeOut() default 120;

    CacheType type() default CacheType.FULL;
}

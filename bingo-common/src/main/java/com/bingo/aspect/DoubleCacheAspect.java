package com.bingo.aspect;

import com.bingo.annotation.DoubleCache;
import com.bingo.enums.CacheType;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


/**
 * @Author 徐志斌
 * @Date: 2023/5/20 21:21
 * @Version 1.0
 * @Description: 两级缓存AOP
 */
@Slf4j
@Aspect
@Component
public class DoubleCacheAspect {
    @Autowired
    private Cache cache;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 二级缓存
     */
    @Around("@annotation(com.bingo.annotation.DoubleCache)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String[] paramNames = signature.getParameterNames();
        Object[] args = point.getArgs();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            treeMap.put(paramNames[i], args[i]);
        }

        // 通过反射，拿到方法上的DoubleCache注解
        DoubleCache annotation = method.getAnnotation(DoubleCache.class);
        // 解析出 key
        String elResult = parse(annotation.key(), treeMap);
        // realKey = 缓存分区名 + key
        String realKey = annotation.cacheName() + ":" + elResult;

        // 如果是删除操作，那就删除；如果不是删除，那就是查询咯！
        if (annotation.type() == CacheType.DELETE) {
            redisTemplate.delete(realKey);
            cache.invalidate(realKey);
            return point.proceed();
        }

        /**-----------------------------------------读取顺序--------------------------------------------*/
        // 1.查询Caffeine
        Object caffeineCache = cache.getIfPresent(realKey);
        if (Objects.nonNull(caffeineCache)) {
            log.info("get data from caffeine");
            return caffeineCache;
        }

        // 2.查询Redis
        Object redisCache = redisTemplate.opsForValue().get(realKey);
        if (Objects.nonNull(redisCache)) {
            log.info("get data from redis");
            cache.put(realKey, redisCache);
            return redisCache;
        }

        // 3.查询MySQL
        log.info("get data from mysql");
        Object object = point.proceed();
        if (Objects.nonNull(object)) {
            redisTemplate.opsForValue().set(realKey, object, annotation.l2TimeOut(), TimeUnit.SECONDS);
            cache.put(realKey, object);
        }
        return object;
    }

    /**
     * 解析
     */
    public static String parse(String elString, TreeMap<String, Object> map) {
        elString = String.format("#{%s}", elString);
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        map.entrySet().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
        Expression expression = parser.parseExpression(elString, new TemplateParserContext());
        return expression.getValue(context, String.class);
    }
}

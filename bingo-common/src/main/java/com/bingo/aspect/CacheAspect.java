package com.bingo.aspect;

import com.bingo.anno.DoubleCache;
import com.bingo.enums.CacheType;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class CacheAspect {
    @Autowired
    private Cache cache;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 切入点：注解
     */
    @Pointcut("@annotation(com.bingo.anno.DoubleCache)")
    public void cacheAspect() {
    }

    /**
     * 环绕通知
     */
    @Around("cacheAspect()")
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
        // 创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 通过evaluationContext.setVariable可以在上下文中设定变量。
        EvaluationContext context = new StandardEvaluationContext();
        map.entrySet().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
        // 解析表达式
        Expression expression = parser.parseExpression(elString, new TemplateParserContext());
        // 使用Expression.getValue()获取表达式的值，这里传入了Evaluation上下文
        String value = expression.getValue(context, String.class);
        return value;
    }
}

package com.bingo.aop;

import com.bingo.annotation.RateLimiter;
import com.bingo.config.LuaScriptConfig;
import com.bingo.enums.LimitType;
import com.bingo.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2023/8/30 22:26
 * @Version 1.0
 * @Description: RateLimitAspect
 * -------------------------------------------------------------------------------
 * 注意：
 * 使用注解AOP切入很奇怪，如果方法参数有注解，那就不能全报名，只能变量名：@Before("@annotation(rateLimiter)")
 * 如果方法参数没有声明注解，那就可以全类名：@Around("@annotation(com.bingo.annotation.DoubleCache)")
 * -------------------------------------------------------------------------------
 *
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    @Qualifier(LuaScriptConfig.RATE_LIMIT)
    private RedisScript<Long> redisScript;

    /**
     * Redis限流：计数器方案
     */
    @Before("@annotation(rateLimiter)")
    public void rateLimiter(JoinPoint point, RateLimiter rateLimiter) throws Exception {
        // key，time，count
        String key = rateLimiter.key();
        int time = rateLimiter.time();
        int count = rateLimiter.count();

        // 获取redis key
        String combineKey = this.getCombineKey(rateLimiter, point);
        List<Object> keys = Collections.singletonList(combineKey);

        // 执行限流 lua 脚本
        Long number = redisTemplate.execute(redisScript, keys, count, time);

        // 判断是否大于限流值
        if (number.equals(null) || number.intValue() > count) {
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), key);
            throw new Exception("操作过于频繁，请稍后再试！");
        }
    }

    /**
     * 获取限流Key
     */
    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            stringBuffer.append(IPUtil.getIpAddress(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}

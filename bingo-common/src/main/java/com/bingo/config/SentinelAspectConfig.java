package com.bingo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 徐志斌
 * @Date: 2023/5/12 22:40
 * @Version 1.0
 * @Description: SentinelAspectConfig
 * 支持通过 @SentinelResource 注解来定义资源，并配置 blockHandler 函数来进行限流之后的处理
 */
@Configuration
public class SentinelAspectConfig {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}

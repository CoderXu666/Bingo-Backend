package com.bingo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @Author 徐志斌
 * @Date: 2023/8/30 22:13
 * @Version 1.0
 * @Description: Redis Lua脚本配置类：将 Lua脚本 放入Spring容器中加载
 */
@Configuration
public class LuaScriptConfig {
    public static final String RATE_LIMIT = "RATE_LIMIT_LUA";

    /**
     * RateLimit.lua限流脚本
     */
    @Bean(RATE_LIMIT)
    public DefaultRedisScript<Long> rateLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/RateLimit.lua")));
        script.setResultType(Long.class);
        return script;
    }
}

package com.bingo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-11  13:03
 * @Description: Jackson配置类
 * @Version: 1.0
 */
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer = builder -> {
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
        return customizer;
    }
}

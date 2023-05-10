package com.bingo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * @Author 徐志斌
 * @Date: 2023/5/10 21:57
 * @Version 1.0
 * @Description: KaptchaConfig
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        // 验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 配置
        Properties properties = new Properties();
        // 是否有边框
        properties.setProperty("kaptcha.border", "yes");
        // 设置边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 验证码
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码文本字符颜色 默认为黑色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 设置字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅 黑");
        // 字体大小，默认40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // 验证码文本字符内容范围 默认为abced2345678gfynmnpwx
        // properties.setProperty("kaptcha.textproducer.char.string", "");
        // 字符长度，默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        // 验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "150");
        // 验证码图片高度 默认为40
        properties.setProperty("kaptcha.image.height", "40");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

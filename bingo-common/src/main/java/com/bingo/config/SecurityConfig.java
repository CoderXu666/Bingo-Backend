package com.bingo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author 徐志斌
 * @Date: 2023/9/18 22:42
 * @Version 1.0
 * @Description: SpringSecurity使用该BCryptPasswordEncoder进行密码校验
 * ----------------------------------------------------------------
 * 理解：
 * 有了它就不用AES加密了！直接用BCryptPasswordEncoder加密即可，密码校验也可以用它
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

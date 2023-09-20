package com.bingo.config;

import com.bingo.security.RestAccessDeniedHandler;
import com.bingo.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author 徐志斌
 * @Date: 2023/9/18 22:42
 * @Version 1.0
 * @Description: SpringSecurity配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /**
     * 前端静态资源， 后端放行API
     */
    private static final String[] STATIC_RESOURCE = {"/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js"};
    private static final String[] API_URL = {"/user/login", "/user/test"};

    /**
     * SpringSecurity相关配置
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 1.禁用CSRF和Session（使用JWT）
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 2.请求授权
        httpSecurity
                .authorizeRequests()
                // 静态资源放行
                .antMatchers(HttpMethod.GET, STATIC_RESOURCE)
                .permitAll()
                // 后端接口放行
                .antMatchers(API_URL)
                .permitAll()
                // OPTIONS请求放行
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                // 测试专用：全部放行
                // .antMatchers("/**")
                // .permitAll()
                // 其他接口需要鉴权
                .anyRequest()
                .authenticated();

        // 3.禁用缓存
        httpSecurity.headers().cacheControl();

        // 4.添加JWT Token过滤器
//        httpSecurity.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class);

        // 5.配置异常处理器
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * BCryptPasswordEncoder：算法bcrypt对密码加密、解密（替代AES）
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理登录认证
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}


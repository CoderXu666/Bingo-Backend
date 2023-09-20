package com.bingo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-20  14:30
 * @Description: Token授权过滤器
 * @Version: 1.0
 * ----------------------------------------------------
 * 通常登录使用InterceptorHandler拦截器处理Token
 * 是用了SpringSecurity本质就是一串Filter，所以这里我使用Filter
 * OncePerRequestFilter：一个请求只被过滤器拦截一次
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");

        // TODO 逻辑

        // 放行过滤器
        chain.doFilter(request, response);
    }
}

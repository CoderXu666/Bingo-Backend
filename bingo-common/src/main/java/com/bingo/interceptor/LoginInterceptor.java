package com.bingo.interceptor;

import com.bingo.context.RequestContextHolder;
import com.bingo.utils.IPUtil;
import com.bingo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/8/21 20:58
 * @Version 1.0
 * @Description: 登录状态-请求拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 请求到达Controller前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // POST请求的 OPTIONS 预请求直接通过
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 校验 Token 不可用
        String token = request.getHeader("token");
        if (!JWTUtil.checkToken(token)) {
            log.info("-------------------------登录Token已过期：FAIL-------------------------");
            return false;
        }

        // 检验 Token 可用
        Map<String, Object> map = JWTUtil.resolveToken(token);
        map.put("ip", IPUtil.getIpAddress(request));
        RequestContextHolder.set(map);
        log.info("-------------------------登录Token校验通过：SUCCESS--------------------------");
        return true;
    }

    /**
     * 调用Controller后，DispatcherServlet 渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * DispatcherServlet 渲染视图后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.remove();
    }
}

package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1.获取请求路径
        String requestURI = request.getRequestURI();
        // 2.判断是否为登录请求，如果路径中包含 /login，则放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 3.获取请求头中的token
        String token = request.getHeader("token");
        log.info("获取到的token：{}", token);
        // 4.判断token是否存在，如果不存在，则返回错误结果(401)
        if(token == null || token.isEmpty()){
            log.info("token不存在，响应401");
            response.setStatus(401);
        }
        // 5.判断token是否存在，如果存在，校验token是否有效
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("token校验失败，响应401");
            response.setStatus(401);
            return;
        }
        // 6.校验通过放行
        log.info("token校验通过，放行");
        filterChain.doFilter(request, response);
    }
}

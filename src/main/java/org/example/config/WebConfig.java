package org.example.config;

import org.example.interceptor.DemoInterceptor;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

/*    @Autowired
    private DemoInterceptor demoInterceptor;*/
/*    @Autowired
    private TokenInterceptor tokenInterceptor;*/
/*    @Override*/
/*    public void addInterceptors(InterceptorRegistry registry) {*/
/*///*        registry.addInterceptor(demoInterceptor)*/
//                .addPathPatterns("/**"); // 拦截哪些请求*/
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**") // 拦截哪些请求
//                .excludePathPatterns("/login"); // 放行哪些请求
/*    }*/
}

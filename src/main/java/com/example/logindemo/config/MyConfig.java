package com.example.logindemo.config;

import com.example.logindemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义配置中心
 */

@Configuration
public class MyConfig implements WebMvcConfigurer {
    /**
     * 加载自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截路径  /**：表示拦截所有请求
                .addPathPatterns("/**")
                //不拦截的请求路径  如：首页的请求、登录、登出等请求，以及静态资源的请求
                .excludePathPatterns("/","/login","/logout","/error","/favicon.ico","/**/*.css","/**/*.js","/images/*","/**/*.html");

    }

}

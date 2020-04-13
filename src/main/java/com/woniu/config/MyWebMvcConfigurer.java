package com.woniu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author R&B
 * @create 2020/03/2020/3/25 14:40:32
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    // 专门用于配置跨域请求的方法
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 表示允许客户端访问当前web应用的任何资源
                .allowedMethods("*")        // 表示允许客户端的所有请求方式： GET POST PUT DELETE OPSTIONS
                .allowedOrigins("*")      // 表示允许指定的IP地址，向当前web应用发送跨域ajax请求
                .allowCredentials(true);    // 允许跨域以及cookie问题解决
    }
}
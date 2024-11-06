package com.gust.cafe.gen.config.springmvc;

import com.gust.cafe.gen.interceptor.LangInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LangInterceptor langInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册国际化拦截器
        registry.addInterceptor(langInterceptor);
    }
}

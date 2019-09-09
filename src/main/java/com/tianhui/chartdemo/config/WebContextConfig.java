package com.tianhui.chartdemo.config;

import com.tianhui.chartdemo.filter.HandlerFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebContextConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new HandlerFilter());
        registration.addPathPatterns("/v1/**").excludePathPatterns("/v1/user/**");
    }
}

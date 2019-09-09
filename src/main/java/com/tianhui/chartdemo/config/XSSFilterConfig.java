package com.tianhui.chartdemo.config;

import com.tianhui.chartdemo.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class XSSFilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(xssFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("XSSFilter");
        return registration;
    }

    @Bean(name = "XSSFilter")
    public Filter xssFilter() {
        return new XSSFilter();
    }
}

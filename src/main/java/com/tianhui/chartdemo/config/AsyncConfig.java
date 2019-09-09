package com.tianhui.chartdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean("transferAsync")
    public Executor transferExecutor(){
        ThreadPoolTaskExecutor transferExecutor = new ThreadPoolTaskExecutor();
        // 线程池初始容量
        transferExecutor.setCorePoolSize(5);
        // 线程池最大容量
        transferExecutor.setMaxPoolSize(20);
        // 缓冲队列
        transferExecutor.setQueueCapacity(200);
        // 设置最大空闲时间
        transferExecutor.setKeepAliveSeconds(60);
        transferExecutor.setThreadNamePrefix("taskExecutor-");
        // 线程池满时，在调用的executor中运行任务
        transferExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return transferExecutor;
    }


    @Bean("refreshTokenAsync")
    public Executor refreshTokenExecutor(){
        ThreadPoolTaskExecutor refreshTokenExecutor = new ThreadPoolTaskExecutor();
        // 线程池初始容量
        refreshTokenExecutor.setCorePoolSize(5);
        // 线程池最大容量
        refreshTokenExecutor.setMaxPoolSize(20);
        // 缓冲队列
        refreshTokenExecutor.setQueueCapacity(100);
        // 设置最大空闲时间
        refreshTokenExecutor.setKeepAliveSeconds(60);
        refreshTokenExecutor.setThreadNamePrefix("taskExecutor-");
        // 线程池满时，在调用的executor中运行任务
        refreshTokenExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return refreshTokenExecutor;
    }
}

package com.tianhui.chartdemo.common;

import org.springframework.beans.factory.annotation.Value;

import java.net.UnknownHostException;

public class SingletonMachine {
    private volatile static SingletonMachine singletonMachine;
    @Value("${server.port}")
    private String localPort;

    private Integer machineId;

    private SingletonMachine() throws UnknownHostException {
        //分布式时需要保证全局唯一，使用machineId,一个应用最多集群为32台，置于数据库或外部缓存
        machineId = 0;
    }

    public static SingletonMachine getInstance() throws UnknownHostException {
        if(null == singletonMachine){
            synchronized (SingletonMachine.class){
                if(null == singletonMachine){
                    singletonMachine = new SingletonMachine();
                }
            }
        }
        return singletonMachine;
    }

    public Integer getNachineId() {
        return machineId;
    }
}

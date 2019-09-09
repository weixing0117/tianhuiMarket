package com.tianhui.chartdemo.utils;


import com.tianhui.chartdemo.common.SingletonMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

public class UIdGenerator {
    private static Logger log = LoggerFactory.getLogger(UIdGenerator.class);

    private volatile static UIdGenerator uIdGenerator;

    private SnowFlakeGenerator snowFlakeGenerator;

    private UIdGenerator() throws UnknownHostException {
        snowFlakeGenerator = new SnowFlakeGenerator(SingletonMachine.getInstance().getNachineId(),0);
    }

    public static UIdGenerator getInstance(){
        if(null == uIdGenerator){
            synchronized (UIdGenerator.class){
                if(null == uIdGenerator){
                    try {
                        uIdGenerator = new UIdGenerator();
                    } catch (UnknownHostException e) {
                        log.error("can't get localAddress:{}"+e);
                    }
                }
            }
        }
        return  uIdGenerator;
    }

    public long getUId(){
        return snowFlakeGenerator.nextId();
    }
}

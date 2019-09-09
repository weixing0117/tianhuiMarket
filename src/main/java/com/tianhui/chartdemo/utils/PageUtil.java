package com.tianhui.chartdemo.utils;

import java.text.DecimalFormat;

public class PageUtil {
    public static final DecimalFormat IntegerFormat = new DecimalFormat("#");
    public static Integer getStartPosition(Integer pageSize,Integer pageNo,Integer count){
        if(pageSize ==null || pageNo == null || count == null){
            return null;
        }
        Integer maxPage = count%pageSize==0?
                Integer.parseInt(IntegerFormat.format(Math.floor(count/pageSize))):Integer.parseInt(IntegerFormat.format(Math.floor(count/pageSize)))+1;
        if(pageNo>maxPage){
            pageNo = maxPage;
        }
        return (pageNo-1)*pageSize;
    }
}

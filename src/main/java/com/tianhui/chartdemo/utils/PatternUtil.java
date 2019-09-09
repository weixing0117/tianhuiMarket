package com.tianhui.chartdemo.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public static boolean isMatch(String regex,String input){
        return Pattern.matches(regex,input);
    }

    public static boolean isCompile(String regex,String input){
        Pattern pattern = Pattern.compile(regex,1);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}

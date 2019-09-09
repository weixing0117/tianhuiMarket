package com.tianhui.chartdemo.utils;


import com.tianhui.chartdemo.bean.UserBean;
import com.tianhui.chartdemo.vo.UserVO;

public class ThreadLocalUtil {
    private static ThreadLocal<String> user_token = new ThreadLocal<>();

    private static ThreadLocal<UserVO> userBean = new ThreadLocal<>();

    public static void setUer_token(String token) {
        user_token.set(token);
    }

    public static String getUser_token() {
        return user_token.get();
    }


    public static UserVO getUser() {
        return userBean.get();
    }

    public static void setUser(UserVO user) {
        userBean.set(user);
    }

    public static void remove() {
        userBean.remove();
        user_token.remove();
    }
}

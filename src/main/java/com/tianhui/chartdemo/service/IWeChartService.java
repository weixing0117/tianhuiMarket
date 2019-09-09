package com.tianhui.chartdemo.service;

import com.alibaba.fastjson.JSONObject;

public interface IWeChartService {
    String getToken(String userCode);

    String checkToken();

    String refreshToken();

    JSONObject getWeChartUserInfo(String accessToken,String openId);

}

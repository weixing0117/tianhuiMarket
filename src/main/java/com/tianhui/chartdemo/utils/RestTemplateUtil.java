package com.tianhui.chartdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateUtil {

    @Autowired
    RestTemplate restTemplate;


    public JSONObject doGet(String url, HttpHeaders httpHeaders){
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        String result = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class).getBody();
        return JSONObject.parseObject(result);
    }

    public JSONObject doPost(String url, Map<String,Object> body, HttpHeaders httpHeaders){
        HttpEntity<String> requestEntity = new HttpEntity<>(JSONObject.toJSONString(body), httpHeaders);
        String result = restTemplate.exchange(url, HttpMethod.POST,requestEntity,String.class).getBody();
        return JSONObject.parseObject(result);
    }
}

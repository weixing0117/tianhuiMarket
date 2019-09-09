package com.tianhui.chartdemo.base;

import com.google.gson.Gson;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.dao.IUserMapper;
import com.tianhui.chartdemo.localEnum.ResponseEnum;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
public class BasicController {
    @Resource
    IUserMapper userMapper;
    Gson gson = new Gson();
    protected ResponseData successConvert(Object data){
        return ResponseData.build(ResponseEnum.SUCCESS,data);
    }
    protected ResponseData noAuthConvert(Object data){
        return ResponseData.build(ResponseEnum.NO_AUTHORIZATION,data);
    }
    protected ResponseData undefinedConvert(Object data){
        return ResponseData.build(ResponseEnum.UNDEFINED,data);
    }
    protected ResponseData customizeConvert(Integer code,String msg,Object data){
        return ResponseData.build(code,msg,data);
    }
    protected boolean isAdmin(){
        // 判断是否是管理员
        return userMapper.isAdmin(ThreadLocalUtil.getUser().getId());
    }

    protected Map<String,Object> constructResult(Integer pageNo, Integer pageSize, Integer count, Object data){
        Map<String,Object> result = new HashMap<>();
        result.put("pageSize",pageSize);
        result.put("pageNo",pageNo);
        result.put("count",count);
        result.put("data",data);
        return result;
    }
}

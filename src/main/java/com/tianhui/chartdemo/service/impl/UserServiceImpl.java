package com.tianhui.chartdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tianhui.chartdemo.dao.IUserMapper;
import com.tianhui.chartdemo.po.UserPO;
import com.tianhui.chartdemo.service.IUserService;
import com.tianhui.chartdemo.utils.EncryptUtil;
import com.tianhui.chartdemo.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    IUserMapper userMapper;

    @Override
    public boolean createUser(UserPO userPO) {
        userPO.setPassword(EncryptUtil.cipherEncrypt(userPO.getPassword()));
        return userMapper.createUser(userPO);
    }

    @Override
    public boolean updateUser(UserPO userPO) {
        //判断是否能更新电话号码

        return userMapper.updateUser(JSONObject.parseObject(JSONObject.toJSONString(userPO),Map.class));
    }

    @Override
    public List<UserVO> queryUser(Long id,String name,Integer telephone,String openId,Integer start,Integer pageSize) {
        return userMapper.queryUser(makeParam(id,name,telephone,openId,start,pageSize));
    }

    @Override
    public Integer queryUserCount(Long id,String name,Integer telephone,String openId) {
        return userMapper.queryUserCount(makeParam(id,name,telephone,openId,null,null));
    }

    @Override
    public String login(String username, String password) {
        String result = userMapper.login(username, EncryptUtil.cipherEncrypt(password));

        if(StringUtils.isEmpty(result)){
            return null;
        }
        return EncryptUtil.cipherEncrypt(result);
    }

    @Override
    public boolean updateCommonAddress(String id, String addressId) {
        return userMapper.updateCommonAddress(id,addressId);
    }

    private Map makeParam(Long id,String name,Integer telephone,String openId,Integer start,Integer pageSize){
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("name",name);
        params.put("telephone",telephone);
        params.put("openId",openId);
        params.put("start",start);
        params.put("pageSize",pageSize);
        return  params;
    }
}

package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.po.UserPO;
import com.tianhui.chartdemo.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface IUserMapper {
     boolean createUser(UserPO userPO);

     boolean updateUser(Map<String,Object> params);

     List<UserVO> queryUser(Map<String, Object> params);

     UserVO queryUserById(String id,String openId,String telephone);

     Integer queryUserCount(Map<String, Object> params);

     String login(String username,String password);

     boolean isAdmin(String id);

     boolean updateCommonAddress(String id,String addressId);
}
